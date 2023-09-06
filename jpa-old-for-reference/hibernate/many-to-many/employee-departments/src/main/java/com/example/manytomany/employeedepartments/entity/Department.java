package com.example.manytomany.employeedepartments.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "dept")
public class Department {

    @Id
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "departments")
    private Set<Employee> employees;
}
