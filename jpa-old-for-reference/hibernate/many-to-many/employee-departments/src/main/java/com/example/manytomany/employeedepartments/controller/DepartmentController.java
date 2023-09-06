package com.example.manytomany.employeedepartments.controller;

import com.example.manytomany.employeedepartments.model.DepartmentData;
import com.example.manytomany.employeedepartments.model.DepartmentInfo;
import com.example.manytomany.employeedepartments.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping(path = "/departments", consumes = MediaType.ALL_VALUE)
    public void createDepartment(DepartmentInfo departmentInfo) {
        departmentService.createDepartment(departmentInfo);
    }

    @GetMapping(path = "/departments/{departmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DepartmentData getDepartment(@PathVariable Long departmentId) {
        return departmentService.getDepartment(departmentId);
    }

    @PutMapping(path = "/departments/{departmentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateDepartment(@PathVariable Long departmentId, DepartmentInfo departmentInfo) {
        departmentService.updateDepartment(departmentId, departmentInfo);
    }

    @DeleteMapping(path = "/departments/{departmentId}")
    public void deleteDepartment(@PathVariable Long departmentId) {
        departmentService.deleteDepartment(departmentId);
    }
}
