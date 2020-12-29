package irishsea.autumnapp.repositories;

import irishsea.autumnapp.domain.Course;
import irishsea.autumnapp.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    List<Course> findAllByUser(User user);
}

