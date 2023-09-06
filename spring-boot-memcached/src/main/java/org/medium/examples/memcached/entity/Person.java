package org.medium.examples.memcached.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person implements Serializable{

    private static final long serialVersionUID = -4439114469417994321L;


    @Id
    @GeneratedValue
    private String id;
    private String name;
    private int age;

    //@Transient
    private double salary;

    }
