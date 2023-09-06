package com.onetoone.example.personaddress.v1.entity;

import jakarta.persistence.Entity;

@Entity
public class Person {

    private Long id;

    private String firstName;

    private String lastName;

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getId() {
        return id;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
