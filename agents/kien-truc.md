# KIẾN TRÚC TỔNG THỂ HỆ THỐNG QUẢN TRỊ NHÂN SỰ (SAAS)

Dựa trên các yêu cầu về tính năng (SaaS, Multi-tenant) và danh sách công nghệ (Spring Boot, React, MySQL, Redis), dưới đây là đề xuất kiến trúc tổng thể cho dự án:

---

## 1. Mô hình Multi-tenancy (Đa thuê bao)
Hệ thống sẽ sử dụng chiến lược **Shared Database, Shared Schema** (Dùng chung database và schema) với cột định danh `tenant_id` (hoặc `company_id`) trong tất cả các bảng dữ liệu nhạy cảm.

*   **Lý do chọn:** Tối ưu hóa chi phí vận hành, dễ dàng bảo trì và upgrade hệ thống đồng loạt cho tất cả các công ty.
*   **Cơ chế cô lập:** Sử dụng **Hibernate Filters** hoặc **Spring Data JPA Interceptors** để tự động thêm điều kiện `WHERE tenant_id = ?` vào mọi câu lệnh truy vấn, đảm bảo công ty A không bao giờ thấy dữ liệu của công ty B.

## 2. Kiến trúc Phân tầng (Layered Architecture)

### A. Client Side (Frontend - React.js)
*   **UI Layer:** Tailwind CSS kết hợp Framer Motion cho trải nghiệm mượt mà.
*   **State Management:** Redux Toolkit quản lý thông tin nhân viên, trạng thái đơn từ và session.
*   **Real-time:** Socket.io-client kết nối với Server để nhận thông báo và chat.
*   **Routing:** React Router xử lý phân quyền URL (ví dụ: `/admin/*`, `/employee/*`).

### B. API Gateway / Security Layer (Spring Security)
*   **Authentication:** Sử dụng JWT (Access Token & Refresh Token). Access Token lưu ở memory (state), Refresh Token lưu ở HttpOnly Cookie để chống XSS.
*   **Authorization:** Phân quyền dựa trên Role (RBAC) kết hợp với `tenant_id` để kiểm tra quyền truy cập tài nguyên.
*   **Rate Limiting:** Sử dụng Redis để ngăn chặn tấn công Brute-force hoặc spam API.

### C. Backend Services (Spring Boot)
*   **Tenant Context:** Một `ThreadLocal` lưu trữ `current_tenant_id` được trích xuất từ JWT của mỗi request.
*   **Business Logic:** Xử lý nghiệp vụ chấm công, tính lương, quy trình duyệt đơn.
*   **WebSocket Server:** Tích hợp Socket.io để xử lý luồng chat và thông báo biến động số dư/duyệt đơn.

### D. Data & Cache Layer
*   **Primary Database (MySQL):** Lưu trữ dữ liệu cấu trúc (nhân viên, bảng lương, cấu hình công ty).
*   **Caching & Session (Redis):** 
    *   Lưu trữ danh sách Token bị thu hồi (Blacklist).
    *   Cache các thông tin ít thay đổi (Danh mục phòng ban, chức vụ) để tăng tốc độ phản hồi.
*   **Storage (S3/MinIO):** Lưu trữ file đính kèm (CV, Hợp đồng, Ảnh đại diện) thay vì lưu trực tiếp trong DB.

---

## 3. Luồng xử lý Real-time (Chat & Notifications)
1.  Nhân viên gửi đơn nghỉ phép -> Backend lưu vào MySQL.
2.  Backend gửi sự kiện (Event) tới **Socket.io Server**.
3.  Socket.io Server định vị `socket_id` của Trưởng phòng dựa trên mapping trong **Redis**.
4.  Gửi thông báo đẩy (Push notification) trực tiếp lên giao diện của Trưởng phòng.

---

## 4. Sơ đồ triển khai (Deployment Architecture)
Hệ thống được đóng gói bằng **Docker** và triển khai theo mô hình:
1.  **Nginx (Reverse Proxy):** Tiếp nhận request, xử lý SSL, cân bằng tải và phục vụ Static files của React.
2.  **App Server (Spring Boot):** Chạy nhiều instance để đảm bảo tính sẵn sàng cao.
3.  **Managed Services:** MySQL và Redis có thể chạy container riêng hoặc dùng dịch vụ Cloud.

---

## 5. Chiến lược Bảo mật (Security Roadmap)
*   **Dữ liệu nhạy cảm:** Mã hóa lương và số CMND trong database (AES-256).
*   **Giao tiếp:** 100% qua HTTPS/WSS.
*   **Audit Trail:** Một bảng `audit_logs` ghi lại: *Ai làm gì, vào lúc nào, cho tenant nào, dữ liệu trước và sau khi thay đổi*.
