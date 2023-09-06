package com.example.manytomany.employeedepartments.repository;

import com.example.manytomany.employeedepartments.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
