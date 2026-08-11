package vn.edu.crs.courseservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    // =========================
    // LAB 3 - SEARCH + PAGINATION
    // =========================

    public Page<CourseDTO> search(
            String keyword,
            Pageable pageable
    ) {

        Page<Course> page;

        if (keyword == null || keyword.isBlank()) {
            page = courseRepository.findAll(pageable);
        } else {
            page = courseRepository
                    .findByTenMonHocContainingIgnoreCase(
                            keyword.trim(),
                            pageable
                    );
        }

        return page.map(this::toDTO);
    }

    // =========================
    // GET BY ID
    // =========================

    public CourseDTO getById(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Không tìm thấy môn học có id = " + id
                        )
                );

        return toDTO(course);
    }

    // =========================
    // CREATE
    // =========================

    public CourseDTO create(CourseDTO dto) {

        boolean existed =
                courseRepository.existsByTenMonHocIgnoreCase(
                        dto.getTenMonHoc()
                );

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
         * Khi tạo môn học:
         * số chỗ còn lại = số chỗ tối đa.
         */
        course.setSoChoConLai(dto.getSoChoToiDa());

        Course savedCourse =
                courseRepository.save(course);

        return toDTO(savedCourse);
    }

    // =========================
    // UPDATE
    // =========================

    public CourseDTO update(
            Long id,
            CourseDTO dto
    ) {

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
         * Không sửa soChoConLai ở đây.
         * Nó chỉ thay đổi khi đăng ký/hủy đăng ký.
         */

        Course updatedCourse =
                courseRepository.save(course);

        return toDTO(updatedCourse);
    }

    // =========================
    // DELETE
    // =========================

    public void delete(Long id) {

        if (!courseRepository.existsById(id)) {
            throw new NoSuchElementException(
                    "Không tìm thấy môn học có id = " + id
            );
        }

        courseRepository.deleteById(id);
    }

    // =========================
    // LAB 3 - RESERVE SEAT
    // =========================

    @Transactional
    public CourseDTO reserveSeat(Long courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Không tìm thấy môn học id = " + courseId
                        )
                );

        if (course.getSoChoConLai() <= 0) {
            throw new IllegalStateException(
                    "Môn học đã hết chỗ, không thể đăng ký"
            );
        }

        course.setSoChoConLai(
                course.getSoChoConLai() - 1
        );

        Course updated =
                courseRepository.save(course);

        return toDTO(updated);
    }

    // =========================
    // LAB 3 - RELEASE SEAT
    // =========================

    @Transactional
    public CourseDTO releaseSeat(Long courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Không tìm thấy môn học id = " + courseId
                        )
                );

        if (course.getSoChoConLai()
                < course.getSoChoToiDa()) {

            course.setSoChoConLai(
                    course.getSoChoConLai() + 1
            );
        }

        Course updated =
                courseRepository.save(course);

        return toDTO(updated);
    }

    // =========================
    // ENTITY -> DTO
    // =========================

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