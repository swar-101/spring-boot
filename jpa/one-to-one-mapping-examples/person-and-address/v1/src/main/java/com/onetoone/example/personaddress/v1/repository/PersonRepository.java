package com.onetoone.example.personaddress.v1.repository;

import com.onetoone.example.personaddress.v1.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}

