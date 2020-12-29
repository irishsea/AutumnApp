package irishsea.autumnapp.controllers;

import irishsea.autumnapp.annotations.Authenticational;
import irishsea.autumnapp.domain.PlainObjects.CoursePojo;
import irishsea.autumnapp.domain.Course;
//import irishsea.autumnapp.exceptions.CustomEmptyDataException;
import irishsea.autumnapp.services.interfaces.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CourseController {

    private final ICourseService сourseService;
    Long userId;

    @Autowired
    public CourseController(ICourseService сourseService) {
        this.сourseService = сourseService;
    }

    @Authenticational
    @PostMapping(path = "/user/{userId}/сourse")
    public ResponseEntity<CoursePojo> createCourse (HttpServletRequest request, @RequestBody Course course, @PathVariable Long userId) {
        CoursePojo result = сourseService.createCourse(course, userId);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Authenticational
    @GetMapping(path = "/user/{userId}/сourse/{id}")
    public ResponseEntity<CoursePojo> getCourse (HttpServletRequest request, @PathVariable Long id) {
        return new ResponseEntity<>(сourseService.getCourse(id, userId), HttpStatus.OK);
    }

    @Authenticational
    @GetMapping(path = "/user/{userId}/сourses")
    public ResponseEntity<List<CoursePojo>> getAll (HttpServletRequest request) {
        return new ResponseEntity<>(сourseService.getAllCourses(userId), HttpStatus.OK);
    }

    @Authenticational
    @PutMapping(path = "/user/{userId}/сourse/{id}")
    public ResponseEntity<CoursePojo> updateCourse (HttpServletRequest request, @RequestBody Course source, @PathVariable Long id) {
        return new ResponseEntity<>(сourseService.updateCourse(source, id, userId), HttpStatus.OK);
    }

    @Authenticational
    @DeleteMapping(path = "/user/{userId}/сourse/{id}")
    public ResponseEntity<String> deleteCourse (HttpServletRequest request, @PathVariable Long id) {
        return new ResponseEntity<>(сourseService.deleteCourse(id, userId), HttpStatus.OK);
    }
    /**
     * Exception handling
     */

//    @ExceptionHandler
//    public ResponseEntity<String> onMissingCourseName (DataIntegrityViolationException exception) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(exception.getClass()) + ": Name of course is obligatory");
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<String> onMissingCourseId(NoSuchElementException exception) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(exception.getClass())
//                + " # "
//                + exception.getLocalizedMessage());
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<String> onMissingCourse (EmptyResultDataAccessException exception) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(exception.getClass()) + ": no one course was found");
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<String> SQLProblems (SQLException exception) {
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(ClassUtils.getShortName(exception.getClass())
//                + exception.getSQLState()
//                + exception.getLocalizedMessage()
//                + ": something went wrong with course");
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<String> customExceptionHandler (CustomEmptyDataException exception) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(exception.getClass())
//                + " "
//                + exception.getCause()
//                + " "
//                + exception.getLocalizedMessage());
//    }
}

