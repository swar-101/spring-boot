package com.example.v1.personandaddress.mapper;

import com.example.v1.personandaddress.entity.Person;
import com.example.v1.personandaddress.model.PersonData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person toEntity(PersonData personData);

    PersonData toDTO(Person person);

    void updatePerson(PersonData personData, Person person);
}
