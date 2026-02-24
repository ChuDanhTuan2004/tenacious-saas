# KẾ HOẠCH TRIỂN KHAI DỰ ÁN QUẢN TRỊ NHÂN SỰ (SAAS)

Dự án này sẽ được chia thành 7 giai đoạn chính nhằm đảm bảo tính ổn định của nền tảng Multi-tenant trước khi phát triển các tính năng nghiệp vụ.

---

## Giai đoạn 1: Thiết lập Nền tảng & Multi-tenancy (Tuần 1)
*Mục tiêu: Xây dựng bộ khung hệ thống có khả năng phân tách dữ liệu các công ty.*

1.  **Backend Base:**
    *   Khởi tạo dự án Spring Boot, tích hợp Spring Data JPA, MySQL.
    *   Cấu hình `TenantContext` dùng `ThreadLocal`.
    *   Thiết lập Hibernate Filter để tự động lọc dữ liệu theo `tenant_id`.
2.  **Database Design:** Thiết kế bảng `tenants` (công ty), `users`, `roles`.
3.  **Frontend Base:**
    *   Khởi tạo React.js với Tailwind CSS.
    *   Thiết lập Layout cơ bản (Sidebar, Header).

## Giai đoạn 2: Bảo mật & Xác thực (Tuần 2)
*Mục tiêu: Đảm bảo người dùng đăng nhập đúng công ty và có đúng quyền.*

1.  **Security Core:** 
    *   Triển khai Spring Security + JWT (Access & Refresh Token).
    *   Xây dựng logic Login/Logout xử lý theo Tenant (xác định tenant qua subdomain hoặc mã công ty).
2.  **RBAC (Role-Based Access Control):** 
    *   Phân quyền Super Admin, Company Admin, Manager, Employee.
3.  **Frontend Auth:** Tích hợp Redux Toolkit, bảo vệ các Route nhạy cảm.

## Giai đoạn 3: Module Nhân sự Cốt lõi - Core HRM (Tuần 3)
*Mục tiêu: Quản lý được thông tin nhân viên và tổ chức.*

1.  **Quản lý Cơ cấu:** API & UI quản lý Phòng ban, Chức vụ.
2.  **Hồ sơ nhân viên:**
    *   Tính năng Thêm/Sửa/Xóa nhân viên.
    *   Upload ảnh đại diện (Tích hợp thư viện cắt ảnh & lưu trữ Cloud/MinIO).
3.  **Quản lý Hợp đồng:** Lưu trữ và nhắc nhở ngày hết hạn.

## Giai đoạn 4: Chấm công & Nghỉ phép (Tuần 4)
*Mục tiêu: Số hóa việc theo dõi thời gian làm việc.*

1.  **Check-in/out:**
    *   Tạo mã QR động phía Backend (thay đổi sau mỗi 30s).
    *   Logic chấm công GPS (kiểm tra khoảng cách giữa user và văn phòng).
2.  **Nghiệp vụ Nghỉ phép:**
    *   API tạo đơn, hủy đơn.
    *   Quy trình duyệt đơn đa cấp giữa Manager và Employee.

## Giai đoạn 5: Tính lương & Báo cáo (Tuần 5)
*Mục tiêu: Tự động hóa bảng tính lương hàng tháng.*

1.  **Cấu hình Lương:** Thiết lập mức lương cơ bản, phụ cấp cho từng nhân viên.
2.  **Bảng lương tháng:** Logic quét dữ liệu chấm công để tính ngày công thực tế và xuất file lương.
3.  **Hệ thống Báo cáo:** Xuất file Excel/PDF danh sách nhân viên và bảng lương bằng iText/Apache POI.

## Giai đoạn 6: Tương tác Real-time & Tiện ích (Tuần 6)
*Mục tiêu: Tăng tính gắn kết trong doanh nghiệp.*

1.  **Hệ thống Chat:** Kết nối Socket.io, tạo phòng chat riêng cho từng bộ phận trong cùng một công ty.
2.  **Thông báo:** Đẩy thông báo Web Push khi có đơn cần duyệt hoặc đến kỳ lương.
3.  **Dashboard (Biểu đồ):** Thống kê biến động nhân sự, tỷ lệ đi muộn bằng Chart.js hoặc Recharts.

## Giai đoạn 7: Kiểm thử & Đóng gói (Tuần 7)
*Mục tiêu: Hoàn thiện sản phẩm để đưa vào sử dụng.*

1.  **Integration Testing:** Kiểm tra kỹ việc rò rỉ dữ liệu giữa các tenant (Data Leakage test).
2.  **UI/UX Polish:** Thêm hiệu ứng Framer Motion cho các chuyển cảnh mượt mà.
3.  **Dockerization:** Viết file `docker-compose` để deploy toàn bộ MySQL, Redis, Backend và Frontend lên VPS.

---

**Lưu ý quan trọng:** Luôn ưu tiên áp dụng **Yoda Condition** trong các logic so sánh backend để đảm bảo đúng style của dự án.
