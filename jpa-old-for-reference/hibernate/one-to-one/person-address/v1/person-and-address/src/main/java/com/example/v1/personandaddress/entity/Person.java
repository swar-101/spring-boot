package com.example.v1.personandaddress.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Data
@Table(name = "v1_person")
public class Person {

    @Id()
    @Column(name = "person_id")
    private Long personId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String dob;

    private Character gender;

    @OneToOne
    private Address address;
}
