package vn.edu.crs.courseservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.crs.courseservice.dto.CourseDTO;
import vn.edu.crs.courseservice.entity.Course;
import vn.edu.crs.courseservice.repository.CourseRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<CourseDTO> getAll() {
        return courseRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public CourseDTO getById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Không tìm thấy môn học có id = " + id
                        )
                );

        return toDTO(course);
    }

    public CourseDTO create(CourseDTO dto) {
        boolean existed = courseRepository
                .existsByTenMonHocIgnoreCase(dto.getTenMonHoc());

        if (existed) {
            throw new IllegalArgumentException(
                    "Tên môn học đã tồn tại"
            );
        }

        Course course = new Course();

        course.setTenMonHoc(dto.getTenMonHoc());
        course.setSoTinChi(dto.getSoTinChi());
        course.setSoChoToiDa(dto.getSoChoToiDa());

        /*
         * Khi tạo mới:
         * số chỗ còn lại bằng số chỗ tối đa.
         *
         * Client không được tự gán giá trị này.
         */
        course.setSoChoConLai(dto.getSoChoToiDa());

        Course savedCourse = courseRepository.save(course);

        return toDTO(savedCourse);
    }

    public CourseDTO update(Long id, CourseDTO dto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Không tìm thấy môn học có id = " + id
                        )
                );

        course.setTenMonHoc(dto.getTenMonHoc());
        course.setSoTinChi(dto.getSoTinChi());
        course.setSoChoToiDa(dto.getSoChoToiDa());

        /*
         * Không cập nhật soChoConLai ở đây.
         * soChoConLai chỉ thay đổi khi đăng ký hoặc hủy đăng ký môn học.
         */

        Course updatedCourse = courseRepository.save(course);

        return toDTO(updatedCourse);
    }

    public void delete(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new NoSuchElementException(
                    "Không tìm thấy môn học có id = " + id
            );
        }

        courseRepository.deleteById(id);
    }

    private CourseDTO toDTO(Course course) {
        return new CourseDTO(
                course.getId(),
                course.getTenMonHoc(),
                course.getSoTinChi(),
                course.getSoChoToiDa(),
                course.getSoChoConLai()
        );
    }
}