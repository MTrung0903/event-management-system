
---


# **Backend - [Tên dự án]**

[![Spring Boot](https://img.shields.io/badge/springboot-2.5.2-green.svg)](https://spring.io/projects/spring-boot)  
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/MTrung0903/event-management-system/actions)
[![Contributors](https://img.shields.io/github/contributors/MTrung0903/event-management-system)](https://github.com/MTrung0903/event-management-system/graphs/contributors)

---

## **Giới thiệu**

Đây là phần backend của dự án **Event Management System**, được xây dựng bằng Spring Boot. Backend chịu trách nhiệm xử lý logic, quản lý cơ sở dữ liệu và cung cấp API để giao tiếp với frontend.

---

## **Các tính năng chính**

- 🌐 **Cung cấp RESTful API** cho Frontend.
- 📂 **Quản lý dữ liệu bằng cơ sở dữ liệu quan hệ**.
- 🔒 **Tích hợp bảo mật với Spring Security.**
- 📈 **Hỗ trợ logging.**

## **Các tính năng mở rộng**
### **Quản lý sự kiện**
- API tạo mới, cập nhật, xóa, và lấy danh sách sự kiện.
- Tìm kiếm sự kiện theo từ khóa, bộ lọc (Trang thái).
- Phân loại sự kiện theo trạng thái (đang diễn ra, sắp tới, đã kết thúc)
- Quản lý thiết bị sự kiện

### **Quản lý nhà cung cấp thiết bị và dịch vụ**
- API quản lý nhà cung cấp thiết bị và dịch vụ: thêm, sửa, xóa, và theo dõi trạng thái thiết bị (đang sử dụng, hỏng, cần bảo trì).
- Tích hợp thông tin nhà cung cấp dịch vụ thuê thiết bị và thời gian hoàn trả.

### **Quản lý nhân sự**

- Gán nhiệm vụ và theo dõi tiến độ công việc của nhân viên theo từng sự kiện.
- Báo cáo hiệu suất làm việc của nhân viên.

### **Thống kê và báo cáo**
Thống kê số lượng sự kiện, khách mời tham dự, và tỷ lệ hoàn thành công việc.
Tính toán chi phí tổ chức sự kiện.

### **Bảo mật và phân quyền**

- Phân quyền chi tiết cho 3 vai trò: Admin, Manager, và Employee.
- Hỗ trợ đăng nhập/đăng xuất với token và refresh token.
- Quản lý phiên đăng nhập an toàn.
- Sử dụng Bcypt để mã hóa mật khẩu

### **Tích hợp dịch vụ bên ngoài**

- Gửi thông báo qua email cho khách mời.
- Đồng bộ sự kiện với Google Calendar.

---

## **Công nghệ sử dụng**

- **Spring Boot** (v2.5 hoặc mới hơn)
- **Spring Data JPA**: Quản lý cơ sở dữ liệu.
- **Spring Security**: Xác thực và phân quyền.
- **MySQL**: Lựa chọn cơ sở dữ liệu.
- **Hibernate ORM**: Framework ánh xạ quan hệ đối tượng, giúp xử lý cơ sở dữ liệu hiệu quả và linh hoạt.
- **Mô hình MVC (Model-View-Controller)**: Kiến trúc tổ chức mã nguồn, giúp chia tách logic nghiệp vụ (Model), xử lý yêu cầu (Controller), và giao diện người dùng (View) để hệ thống dễ bảo trì và mở rộng.

---
