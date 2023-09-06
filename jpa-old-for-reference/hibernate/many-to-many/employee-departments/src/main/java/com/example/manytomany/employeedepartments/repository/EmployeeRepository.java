package com.example.manytomany.employeedepartments.repository;

import com.example.manytomany.employeedepartments.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
