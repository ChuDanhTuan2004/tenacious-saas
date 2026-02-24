# DANH SÁCH TÍNH NĂNG HỆ THỐNG QUẢN TRỊ NHÂN SỰ (SAAS MODEL)
## Tên doanh nghiệp của chúng ta: Tenacious

Hệ thống được thiết kế theo mô hình **Multi-tenant**, cho phép cung cấp dịch vụ quản lý nhân sự cho nhiều công ty khác nhau trên cùng một nền tảng.

---

## 1. Cấp độ Hệ thống (Super Admin - Nhà cung cấp dịch vụ)
Dành cho quản trị viên tối cao của nền tảng để quản lý toàn bộ hệ thống.

*   **Quản lý Doanh nghiệp (Tenant Management):**
    *   Phê duyệt/Khóa tài khoản các công ty đăng ký dịch vụ.
    *   Thiết lập subdomain hoặc mã định danh riêng cho từng công ty.
*   **Quản lý Gói dịch vụ (Subscription Plans):**
    *   Định nghĩa các gói (Basic, Professional, Enterprise) theo số lượng nhân viên hoặc tính năng.
    *   Theo dõi hạn sử dụng và lịch sử thanh toán của mỗi công ty.
*   **Báo cáo Tổng quát & Giám sát:**
    *   Thống kê số lượng người dùng, dung lượng lưu trữ đang sử dụng.
    *   Giám sát logs hệ thống và bảo mật.

## 2. Cấp độ Doanh nghiệp (Company Admin - Quản trị viên công ty)
Dành cho HR hoặc Giám đốc công ty khách hàng để thiết lập môi trường làm việc riêng.

*   **Cấu hình Tổ chức:**
    *   Tùy chỉnh sơ đồ tổ chức (Phòng ban, Tổ đội, Chi nhánh).
    *   Thiết lập danh mục chức vụ, cấp bậc và dải lương cơ bản.
*   **Quản lý Phân quyền (Internal RBAC):**
    *   Tạo các vai trò tùy chỉnh (HR Manager, Dept Head, Accountant, Employee).
    *   Gán quyền chi tiết (Xem/Sửa/Xóa/Duyệt) theo từng module.
*   **Cấu hình Quy trình (Workflow Setup):**
    *   Thiết lập quy trình duyệt đơn (Duyệt 1 cấp, đa cấp).
    *   Cấu hình quy định chấm công (Giờ làm việc, ngày nghỉ lễ, mức phạt đi muộn).

## 3. Các Module Nghiệp vụ Core (Cho mọi công ty)

### A. Quản lý Hồ sơ & Nhân sự (Core HRM)
*   **Hồ sơ số hóa:** Lưu trữ thông tin cá nhân, bằng cấp, ảnh chân dung (hỗ trợ AI crop ảnh).
*   **Quản lý Hợp đồng:** Theo dõi loại hợp đồng (thử việc, chính thức), ngày hết hạn và tự động nhắc nhở gia hạn.
*   **Quản lý Tài sản:** Cấp phát trang thiết bị (laptop, đồng phục) cho nhân viên.

### B. Chấm công & Nghỉ phép (Time & Attendance)
*   **Chấm công đa hình thức:**
    *   Check-in/out qua mã QR động (thay đổi theo thời gian thực để tránh gian lận).
    *   Chấm công qua định vị GPS (cho nhân viên đi thị trường).
    *   Tích hợp máy chấm công vân tay (API).
*   **Quản lý Nghỉ phép:** Gửi đơn và duyệt nghỉ phép/tăng ca/đi muộn chuyên nghiệp ngay trên Mobile App/Web.

### C. Tính lương & Phúc lợi (Payroll & Benefits)
*   **Công thức lương linh hoạt:** Cho phép Admin cấu hình công thức tính lương dựa trên ngày công, phụ cấp, bảo hiểm và thuế (PIT).
*   **Phiếu lương (Payslip):** Tự động gửi phiếu lương bảo mật qua Email hoặc thông báo trực tiếp trên ứng dụng.

### D. Tuyển dụng & Onboarding
*   **Quy trình Tuyển dụng:** Đăng tin, quản lý CV ứng viên, lịch hẹn phỏng vấn.
*   **Quy trình Thử việc:** Tự động tạo checklist công việc cho người mới (Onboarding tasks).

## 4. Tính năng Tương tác & Tiện ích (Value-added Features)

*   **Chat nội bộ & Truyền thông:**
    *   Chat real-time (Socket.io) theo cá nhân hoặc nhóm phòng ban.
    *   Bảng tin nội bộ (Newsfeed) để thông báo các chính sách mới của công ty.
*   **Quản lý Công việc & KPI:**
    *   Giao việc, nhắc lịch (To-do list).
    *   Đánh giá nhân viên theo KPI hoặc OKRs hàng tháng/quý.
*   **Hệ thống Thông báo (Notification System):**
    *   Thông báo Real-time (Web push/Email) khi có đơn cần duyệt, đến kỳ trả lương hoặc sinh nhật nhân viên.
*   **Xuất báo cáo (Smart Export):** Xuất dữ liệu đa dạng (Excel, PDF) với mẫu thiết kế chuyên nghiệp của từng doanh nghiệp.

## 5. Bảo mật & Hạ tầng
*   **Multi-tenancy Security:** Đảm bảo dữ liệu giữa các công ty được cô lập hoàn toàn (Data Isolation).
*   **Xác thực:** JWT kết hợp với Refresh Token, hỗ trợ xác thực 2 lớp (2FA) cho tài khoản Admin.
*   **Nhật ký hoạt động (Audit Logs):** Ghi lại mọi hành động nhạy cảm (thay đổi lương, xóa nhân sự) để truy vết khi cần.
