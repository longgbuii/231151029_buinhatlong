package vn.edu.crs.courseservice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vn.edu.crs.courseservice.entity.Course;
import vn.edu.crs.courseservice.repository.CourseRepository;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CourseRepository courseRepository;

    public DataSeeder(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (courseRepository.count() == 0) {
            Course course1 = new Course(null, "Lập trình Web với React", 3, 50, 50);
            Course course2 = new Course(null, "Kiến trúc Microservices", 4, 40, 40);
            Course course3 = new Course(null, "Cơ sở dữ liệu nâng cao", 3, 60, 60);
            Course course4 = new Course(null, "Phát triển ứng dụng di động", 3, 45, 45);

            courseRepository.saveAll(List.of(course1, course2, course3, course4));
            System.out.println("DataSeeder: Da them 4 mon hoc mau vao database.");
        }
    }
}
