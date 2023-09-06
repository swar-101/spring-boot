package com.example.manytomany.employeedepartments.model;

import com.example.manytomany.employeedepartments.entity.Department;
import lombok.Data;

import java.util.Set;

@Data
public class EmployeeData {

    private Long id;

    private String firstName;

    private String lastName;

    private Set<Department> departments;
}
