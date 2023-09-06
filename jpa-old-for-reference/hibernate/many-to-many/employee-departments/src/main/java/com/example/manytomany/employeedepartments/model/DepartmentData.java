package com.example.manytomany.employeedepartments.model;

import com.example.manytomany.employeedepartments.entity.Employee;
import lombok.Data;

import java.util.Set;

@Data
public class DepartmentData {

    private Long id;

    private String name;

    private Set<Employee> employees;
}
