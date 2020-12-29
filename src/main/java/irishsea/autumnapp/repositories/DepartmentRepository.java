package irishsea.autumnapp.repositories;

import irishsea.autumnapp.domain.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
    List<Department> findByName(String name);
}

