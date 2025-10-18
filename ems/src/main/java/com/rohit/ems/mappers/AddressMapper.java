package com.rohit.ems.mappers;

import org.mapstruct.Mapper;

import com.rohit.ems.dtos.AddressResult;
import com.rohit.ems.entities.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address resultToEntity(AddressResult addressResult);

    AddressResult entityToResult(Address address);
}
