package com.example.v1.personandaddress.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

@Entity
@Table(name = "v1_address")
public class Address {
    
    @Id
    @MapsId("person_id")
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "line1")
    private String address1;

    @Column(name = "line2")
    private String address2;

    private String city;

    private String state;

    private String country;

    private String zipcode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="person_id")
    private Person person;
}
