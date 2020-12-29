package irishsea.autumnapp.services.interfaces;


import irishsea.autumnapp.domain.Department;

public interface IDepartmentService {

    Department findOrCreate(Department department);
}
