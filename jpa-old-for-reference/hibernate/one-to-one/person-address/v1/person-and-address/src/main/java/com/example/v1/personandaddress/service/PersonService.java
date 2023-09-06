package com.example.v1.personandaddress.service;

import com.example.v1.personandaddress.entity.Person;
import com.example.v1.personandaddress.mapper.PersonMapper;
import com.example.v1.personandaddress.model.PersonData;
import com.example.v1.personandaddress.model.PersonResponse;
import com.example.v1.personandaddress.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonService {

    private final PersonMapper personMapper;
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonMapper personMapper, PersonRepository personRepository) {
        this.personMapper = personMapper;
        this.personRepository = personRepository;
    }

    public PersonResponse createPerson(PersonData personData) {

        Person person = personMapper.toEntity(personData);

        Person data = personRepository.save(person);

//        PersonResponse personResponse = new PersonResponse();

        PersonResponse personResponse = null;
        personResponse.setPersonId(data.getPersonId());

        return personResponse;
    }

    public PersonResponse updatePerson(Long personId, PersonData personData) {

        PersonResponse personResponse = null;
        return personResponse;
    }
}
