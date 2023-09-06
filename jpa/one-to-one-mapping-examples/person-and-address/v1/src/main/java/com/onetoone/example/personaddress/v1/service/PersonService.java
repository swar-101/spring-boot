package com.onetoone.example.personaddress.v1.service;

import com.onetoone.example.personaddress.v1.entity.Person;
import com.onetoone.example.personaddress.v1.model.PersonData;
import com.onetoone.example.personaddress.v1.model.PersonResponse;
import com.onetoone.example.personaddress.v1.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonResponse createPerson(PersonData personData) {
        Person person = new Person();
        person.setFirstName(personData.getFirstName());
        person.setLastName(personData.getLastName());

        personRepository.save(person);

        PersonResponse personResponse = new PersonResponse();
        personResponse.setPersonId(person.getId());

        return personResponse;
    }

    public PersonData getPerson(Long personId) {
        Optional<Person> optionalPerson = personRepository.findById(personId);
        PersonData personData = new PersonData();

        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            personData.setFirstName(person.getFirstName());
            personData.setLastName(personData.getLastName());
        }

        return personData;
    }

    public PersonResponse updatePerson(Long personId, PersonData personData) {
        Optional<Person> optionalPerson = personRepository.findById(personId);
        PersonResponse personResponse = new PersonResponse();

        if(optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            person.setFirstName(personData.getFirstName());
            person.setLastName(personData.getLastName());
            personResponse.setPersonId(person.getId());
        }

        return personResponse;
    }

    public void deletePerson(Long personId) {
        Optional<Person> optionalPerson = personRepository.findById(personId);

        if(optionalPerson.isPresent()) {
            Person person = optionalPerson.get();

            personRepository.delete(person);
        }
    }
}
