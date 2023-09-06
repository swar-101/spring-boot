package com.onetoone.example.personaddress.v1.repository;

import com.onetoone.example.personaddress.v1.entity.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
}
