# API Blueprint

## Course Service

| Method | Endpoint | Mô tả |
|---------|----------|-------|
| GET | /courses | Danh sách môn học |
| GET | /courses/{id} | Chi tiết môn học |
| POST | /courses | Thêm môn học |
| PUT | /courses/{id} | Cập nhật môn học |
| DELETE | /courses/{id} | Xóa môn học |

---

## Registration Service

| Method | Endpoint | Mô tả |
|---------|----------|-------|
| POST | /registrations | Đăng ký học |
| GET | /registrations | Danh sách đăng ký |
| DELETE | /registrations/{id} | Hủy đăng ký |

### Internal API

| Method | Endpoint | Mô tả |
|---------|----------|-------|
| POST | /internal/reserve-seat | Giữ chỗ |
| POST | /internal/release-seat | Trả chỗ |

---

## Student Service

| Method | Endpoint | Mô tả |
|---------|----------|-------|
| GET | /students | Danh sách sinh viên |
| GET | /students/{id} | Chi tiết sinh viên |
| POST | /students | Thêm sinh viên |
| PUT | /students/{id} | Cập nhật sinh viên |
| DELETE | /students/{id} | Xóa sinh viên |