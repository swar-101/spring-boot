package com.onetoone.example.personaddress.v1.entity;

import jakarta.persistence.Entity;

@Entity
public class Address {

    private Long addressId;

    private Long line1;

    private Long line2;

    private String city;

    private String state;

    private String country;

    private String zipcode;
}
