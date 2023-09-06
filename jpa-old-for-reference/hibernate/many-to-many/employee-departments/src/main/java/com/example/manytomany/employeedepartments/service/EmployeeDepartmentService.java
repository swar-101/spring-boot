package com.example.manytomany.employeedepartments.service;

import com.example.manytomany.employeedepartments.entity.Department;
import com.example.manytomany.employeedepartments.entity.Employee;
import com.example.manytomany.employeedepartments.repository.DepartmentRepository;
import com.example.manytomany.employeedepartments.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeDepartmentService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeDepartmentService(EmployeeRepository employeeRepository,
                                     DepartmentRepository departmentRepository) {

        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public void assignDepartmentToEmployee(Long employeeId, Long departmentId) {
        Optional<Employee> thatEmployee = employeeRepository.findById(employeeId);
        if (thatEmployee.isPresent()) {
            Optional<Department> queriedDepartment = departmentRepository
                                                        .findById(departmentId);

            if (queriedDepartment.isPresent()) {
                Department department = queriedDepartment.get();
                Set<Employee> employeeSet = new HashSet<>();
                Employee employee = thatEmployee.get();
                employeeSet.add(employee);
                department.setEmployees(employeeSet);

                departmentRepository.save(department);
            }
            else; // write department exception
        }
        else; // write employee exception
    }

    public void deallocateEmployeeFromDepartments(Long employeeId) {
        Optional<Employee> thatEmployee = employeeRepository.findById(employeeId);
        if (thatEmployee.isPresent()) {
            Employee employee = thatEmployee.get();

            Set<Department> departments = employee.getDepartments();
            departments.clear();

            employee.setDepartments(departments);
            employeeRepository.save(employee);
        }
        else; // write employee exception
    }
}
