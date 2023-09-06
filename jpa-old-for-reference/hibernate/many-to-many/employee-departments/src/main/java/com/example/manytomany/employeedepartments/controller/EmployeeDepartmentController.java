package com.example.manytomany.employeedepartments.controller;

import com.example.manytomany.employeedepartments.service.EmployeeDepartmentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeDepartmentController {

    private final EmployeeDepartmentService employeeDepartmentService;

    public EmployeeDepartmentController(EmployeeDepartmentService employeeDepartmentService) {
        this.employeeDepartmentService = employeeDepartmentService;
    }

    @PutMapping(path = "/employees/{employeeId}/departments/{departmentId}", consumes = MediaType.ALL_VALUE)
    public void assignDepartmentToEmployee(@PathVariable Long employeeId,
                                           @PathVariable Long departmentId) {
        employeeDepartmentService.assignDepartmentToEmployee(employeeId, departmentId);
    }

    @PutMapping(path = "/employees/{employeeId}/departments", consumes = MediaType.ALL_VALUE)
    public void deallocateEmployeeFromDepartments(@PathVariable Long employeeId) {
        employeeDepartmentService.deallocateEmployeeFromDepartments(employeeId);
    }
}
