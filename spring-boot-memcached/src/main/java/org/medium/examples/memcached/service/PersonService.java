package org.medium.examples.memcached.service;

import java.util.Objects;
import org.medium.examples.memcached.entity.Person;
import org.medium.examples.memcached.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;

import org.medium.examples.memcached.service.PersonNotFoundException;


@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Cacheable("personCache")
    public Person getPerson(String id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person Not Found"));
           
        if (Objects.nonNull(person)) {
            double salary = 5000.00;
            person.setSalary(salary);
        }
        return person;
    }
    
    //@Cacheable("personCache")
    @CachePut(value="personCache", key="#personId")
    public Person updatePerson(Person per, String personId) {
    	Person person = personRepository.findById(personId)
            .orElseThrow(() -> new PersonNotFoundException("Person Not Found"));
        person.setAge(per.getAge());
    	person.setName(per.getName());
    	person.setSalary(per.getSalary());
       return personRepository.save(person);
    }
    
    //@Override
    @CacheEvict(value="personCache", key="#id")
    // @CacheEvict(value="Invoice", allEntries=true) //in case there are multiple records to delete
    public void deletePerson(String id) {
    	Person person = personRepository.findById(id)
           .orElseThrow(() -> new PersonNotFoundException("Person Not Found"));
    	personRepository.delete(person);
    }

}
