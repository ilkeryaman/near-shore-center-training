package com.nsc.customer.converter.mapstruct;

import com.nsc.customer.model.customer.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAddressMapper {
    Address generateToModel(com.nsc.swagger.address_service_api.model.Address address);
}
