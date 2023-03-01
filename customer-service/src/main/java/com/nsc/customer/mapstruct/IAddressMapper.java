package com.nsc.customer.mapstruct;

import com.nsc.swagger.address_service_api.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAddressMapper {
    com.nsc.customer.model.customer.Address generateToModel(Address address);
}