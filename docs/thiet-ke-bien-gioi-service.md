# Thiết kế biên giới Service

## Kiến trúc dự kiến

Hệ thống được chia thành 4 thành phần:

1. API Gateway
2. Course Service
3. Registration Service
4. Student Service

---

## Database riêng

Mỗi service có database riêng:

| Service | Database |
|----------|-----------|
| Course Service | course_db |
| Registration Service | registration_db |
| Student Service | student_db |

---

## Gateway Routing

| URL | Service |
|------|---------|
| /courses/** | Course Service |
| /registrations/** | Registration Service |
| /students/** | Student Service |

---

## Giao tiếp

Gateway nhận request từ Client và chuyển tiếp đến Service tương ứng.

Mỗi Service chỉ truy cập database của chính nó.