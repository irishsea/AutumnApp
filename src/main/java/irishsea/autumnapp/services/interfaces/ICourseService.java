package irishsea.autumnapp.services.interfaces;

import irishsea.autumnapp.domain.PlainObjects.CoursePojo;
import irishsea.autumnapp.domain.Course;

import java.util.List;

public interface ICourseService {

    CoursePojo createCourse(Course course, Long userId);
    CoursePojo getCourse(Long id, Long userId);
    List<CoursePojo> getAllCourses(Long userId);
    CoursePojo updateCourse(Course course, Long CourseId, Long userId);
    String deleteCourse(Long id, Long userId);
}

