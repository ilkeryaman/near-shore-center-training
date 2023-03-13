package com.nsc.billing.model.address.converter;

import com.nsc.billing.model.address.Address;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AddressConverter implements AttributeConverter<Address, Long> {
    @Override
    public Long convertToDatabaseColumn(Address address) {
        return address.getId();
    }

    @Override
    public Address convertToEntityAttribute(Long id) {
        Address address = new Address();
        address.setId(id);
        return address;
    }
}
