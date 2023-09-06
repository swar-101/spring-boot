package com.example.v1.personandaddress.controller;

import com.example.v1.personandaddress.model.PersonData;
import com.example.v1.personandaddress.model.PersonResponse;
import com.example.v1.personandaddress.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(path = "/person")
    public ResponseEntity<PersonResponse> createPerson(PersonData personData) {
        PersonResponse personResponse = personService.createPerson(personData);

        if(Objects.isNull(personData)) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>(personResponse, HttpStatus.OK);
    }

    @PutMapping(path = "/person/{personId}")
    public ResponseEntity<PersonResponse> updatePerson(@PathVariable Long personId,
                                                       @RequestBody PersonData personData) {
        PersonResponse personResponse = personService.updatePerson(personId, personData);

        return new ResponseEntity<>(personResponse, HttpStatus.OK);
    }
}
