package com.example.manytomany.employeedepartments.service;

import com.example.manytomany.employeedepartments.entity.Department;
import com.example.manytomany.employeedepartments.model.DepartmentData;
import com.example.manytomany.employeedepartments.model.DepartmentInfo;
import com.example.manytomany.employeedepartments.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void createDepartment(DepartmentInfo departmentInfo) {
        Department department = new Department();
        department.setId(departmentInfo.getId());
        department.setName(departmentInfo.getName());

        departmentRepository.save(department);
    }

    public DepartmentData getDepartment(Long employeeId) {
        Optional<Department> queriedDepartment = departmentRepository.findById(employeeId);

        if (queriedDepartment.isPresent()) {
            Department foundDepartment = queriedDepartment.get();
            DepartmentData departmentData = new DepartmentData();
            departmentData.setId(foundDepartment.getId());
            departmentData.setName(foundDepartment.getName());

            return departmentData;
        }
        else return null;
    }

    public void updateDepartment(Long employeeId, DepartmentInfo departmentInfo) {
        Optional<Department> queriedDepartment = departmentRepository.findById(employeeId);

        if (queriedDepartment.isPresent()) {
            Department department = queriedDepartment.get();
            department.setId(department.getId());
            department.setName(department.getName());

            departmentRepository.save(department);
        }
        else ; //try writing custom exception
    }

    public void deleteDepartment(Long employeeId) {
        Optional<Department> queriedDepartment = departmentRepository.findById(employeeId);

        if (queriedDepartment.isPresent()) departmentRepository.delete(queriedDepartment.get());
    }
}
