package com.example.v1.personandaddress.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressData {

    private String address1;

    private String address2;

    private String city;

    private String state;
}
