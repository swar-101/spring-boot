package com.example.estoredataservice.entity;

import org.springframework.data.mongodb.core.mapping.*;

@Document("users")
public class User {

    private Long id;
    private String name;
    private String email;

}
