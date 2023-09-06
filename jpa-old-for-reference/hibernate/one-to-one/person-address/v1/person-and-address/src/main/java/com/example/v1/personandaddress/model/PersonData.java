package com.example.v1.personandaddress.model;

import com.example.v1.personandaddress.entity.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonData {

    private String firstName;

    private String lastName;

    private String dob;

    private Character gender;

    @JsonIgnore
    private Address address;
}
