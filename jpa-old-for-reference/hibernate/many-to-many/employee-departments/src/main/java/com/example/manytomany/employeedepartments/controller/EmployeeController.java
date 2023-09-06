package com.example.manytomany.employeedepartments.controller;

import com.example.manytomany.employeedepartments.model.EmployeeData;
import com.example.manytomany.employeedepartments.model.EmployeeInfo;
import com.example.manytomany.employeedepartments.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(path = "/employees", consumes = MediaType.ALL_VALUE)
    public void createEmployee(EmployeeInfo employeeInfo) {

        employeeService.createEmployee(employeeInfo);
    }

    @GetMapping(path = "/employees/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeData getEmployee(Long employeeId) {

        return employeeService.getEmployee(employeeId);
    }

    @PutMapping(path = "/employees/{employeeId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateEmployee(Long employeeId, EmployeeData employeeData) {

        employeeService.updateEmployee(employeeId, employeeData);
    }

    @DeleteMapping(path = "/employees/{employeeId}")
    public void deleteEmployee(Long employeeId) {

        employeeService.deleteEmployee(employeeId);
    }
}
