package com.nsc.address.validation.validator;

import com.nsc.address.enums.address.City;
import com.nsc.address.model.address.Address;
import com.nsc.address.validation.AddressValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class AddressValidator implements ConstraintValidator<AddressValidation, Address>
{
    @Override
    public boolean isValid(Address address, ConstraintValidatorContext context) {
        return Arrays.stream(City.values()).anyMatch(c -> c.getValue().equals(address.getCity()));
    }
}
