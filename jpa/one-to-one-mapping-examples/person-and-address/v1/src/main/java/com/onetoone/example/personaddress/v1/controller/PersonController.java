package com.onetoone.example.personaddress.v1.controller;

import com.onetoone.example.personaddress.v1.model.PersonData;
import com.onetoone.example.personaddress.v1.model.PersonResponse;
import com.onetoone.example.personaddress.v1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/person")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(path = "/person")
    public ResponseEntity<PersonResponse> createPerson(PersonData personData) {

        return new ResponseEntity<>(personService.createPerson(personData), HttpStatus.OK);
    }

    @GetMapping(path = "/person/{personId}")
    public ResponseEntity<PersonData> getPerson(@PathVariable Long personId) {

        return new ResponseEntity<>(personService.getPerson(personId), HttpStatus.OK);
    }


    @PutMapping(path = "/person/{personId}")
    public ResponseEntity<PersonResponse> updatePerson(@PathVariable Long personId, @RequestBody PersonData personData) {

        return new ResponseEntity<>(personService.updatePerson(personId, personData), HttpStatus.OK);
    }
}