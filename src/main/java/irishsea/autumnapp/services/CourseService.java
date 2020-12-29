package irishsea.autumnapp.services;

import irishsea.autumnapp.domain.PlainObjects.CoursePojo;
import irishsea.autumnapp.domain.Department;
import irishsea.autumnapp.domain.Course;
import irishsea.autumnapp.domain.User;
import irishsea.autumnapp.exceptions.CustomEmptyDataException;
import irishsea.autumnapp.repositories.CourseRepository;
import irishsea.autumnapp.repositories.UserRepository;
import irishsea.autumnapp.services.interfaces.IDepartmentService;
import irishsea.autumnapp.services.interfaces.ICourseService;
import irishsea.autumnapp.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseService implements ICourseService {

    private final Converter converter;
    private final IDepartmentService departmentService;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Autowired
    public CourseService(Converter converter,
                         IDepartmentService departmentService,
                         CourseRepository courseRepository,
                         UserRepository userRepository) {
        this.converter = converter;
        this.departmentService = departmentService;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public CoursePojo createCourse(Course course, Long userId) {

        Optional<User> courseUserOptional = userRepository.findById(userId);

        if (courseUserOptional.isPresent()) {

            Set<Department> departments = new HashSet<>();
            departments.addAll(course.getDepartmentList());

            course.getDepartmentList().clear();

            course.setUser(courseUserOptional.get());
            courseRepository.save(course);

            departments.stream().map(department -> departmentService.findOrCreate(department)).collect(Collectors.toSet()).forEach(course::addDepartment);

            return converter.courseToPojo(course);
        } else {
            throw new CustomEmptyDataException("unable to get user for course");
        }
    }


    @Override
    @Transactional
    public CoursePojo getCourse(Long id, Long userId) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (courseOptional.isPresent()) {
            return converter.courseToPojo(courseOptional.get());
        }
        throw new NoSuchElementException("unable to get course");
    }


    @Override
    @Transactional
    public CoursePojo updateCourse(Course source, Long courseId, Long userId) {

        Optional<Course> targetOptional = courseRepository.findById(courseId);

        if (targetOptional.isPresent()) {
            Course target = targetOptional.get();

            target.setName(source.getName());
            target.setComment(source.getComment());
            target.setStartDate(source.getStartDate());
            target.setEndDate(source.getEndDate());
            target.setPrivacy(source.getPrivacy());

            courseRepository.save(target);

            return converter.courseToPojo(target);
        } else {
            throw new NoSuchElementException("unable to update course");
        }
    }

    @Override
    @Transactional
    public String deleteCourse(Long id, Long userId) {

        Optional<Course> courseForDeleteOptional = courseRepository.findById(id);
        if(courseForDeleteOptional.isPresent()) {
            Course courseForDelete = courseForDeleteOptional.get();
            courseForDelete.getDepartmentList().stream().collect(Collectors.toList()).forEach(department -> department.removeCourse(courseForDelete));
            courseRepository.delete(courseForDelete);
            return "Course with id:" + id + " was successfully removed";
        } else {
            throw new NoSuchElementException("unable to delete course");
        }
    }

    @Override
    @Transactional
    public List<CoursePojo> getAllCourses(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return courseRepository.findAllByUser(userOptional.get()).stream().map(course -> converter.courseToPojo(course)).collect(Collectors.toList());
        } else {
            throw new NoSuchElementException("No user with id: " + userId + " was found");
        }
    }
}

