# Course Registration System (CRS) - Microservices

Đây là hệ thống Đăng ký học phần (Course Registration System) được xây dựng theo kiến trúc **Microservices** kết hợp giữa Backend **Spring Boot** và Frontend **ReactJS**.

## 🏗 Cấu trúc hệ thống

Dự án bao gồm các service độc lập giao tiếp với nhau thông qua API Gateway:

- **`api-gateway` (Port 8080):** Cổng giao tiếp trung tâm (Spring Cloud Gateway), chịu trách nhiệm định tuyến (routing) request từ Frontend đến các service tương ứng và xử lý CORS tập trung.
- **`auth-service` (Port 8081):** Dịch vụ xác thực và phân quyền. Cung cấp tính năng đăng nhập và cấp phát, xác minh JWT (JSON Web Token) cho người dùng (Sinh viên, Admin).
- **`course-service` (Port 8082):** Dịch vụ quản lý khóa học. Xử lý logic nghiệp vụ liên quan đến việc xem danh sách môn học, tìm kiếm, phân trang và quản lý số lượng chỗ trống.
- **`registration-service` (Port 8083):** Dịch vụ đăng ký học phần. Quản lý việc đăng ký/huỷ môn học của sinh viên, đảm bảo tính nhất quán dữ liệu.
- **`crs-frontend` (Port 5173):** Giao diện người dùng được xây dựng bằng **Vite + React + TypeScript**. Đóng vai trò là Client duy nhất giao tiếp với toàn bộ hệ thống thông qua `api-gateway`.

## 🚀 Công nghệ sử dụng

### Backend
- **Java & Spring Boot** (Spring MVC, Spring Data JPA, Spring Cloud Gateway)
- **Database:** MySQL
- **Bảo mật:** Spring Security & JWT (JSON Web Token)

### Frontend
- **ReactJS & TypeScript** (Khởi tạo bằng Vite)
- **HTTP Client:** Axios
- **Routing:** React Router DOM

## 🛠 Hướng dẫn chạy dự án

1. **Khởi động Backend:** Mở dự án trong IntelliJ IDEA và chạy lần lượt cả 4 service: `course-service`, `auth-service`, `registration-service`, và `api-gateway`. Đảm bảo MySQL đang chạy và đã cấu hình đúng tài khoản/mật khẩu trong file `application.yml` của các service.
2. **Khởi động Frontend:**
   ```bash
   cd crs-frontend
   npm install
   npm run dev
   ```
3. Truy cập vào `http://localhost:5173` trên trình duyệt để sử dụng hệ thống.

---
*Dự án thực hành môn học / Lab Project.*
