package com.example.manytomany.employeedepartments.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "emp")
public class Employee {

    @Id
    private Long id;

    @Column(name = "fname")
    private String firstName;

    @Column(name = "lname")
    private String lastName;

    @ManyToMany
    @JoinTable(
            name = "emp_dept",
            joinColumns = @JoinColumn(name = "emp_id"),
            inverseJoinColumns = @JoinColumn(name = "dept_id"))
    private Set<Department> departments;
}
