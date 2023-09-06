package com.example.v1.personandaddress.mapper;

import com.example.v1.personandaddress.entity.Address;
import com.example.v1.personandaddress.model.AddressData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toEntity(AddressData addressData);

    AddressData toDTO(Address address);

    void updateAddress(AddressData addressData, Address address);
}
