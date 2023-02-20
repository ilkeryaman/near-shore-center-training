package com.nsc.customer.validation.validator;

import com.nsc.customer.enums.address.City;
import com.nsc.customer.model.customer.Address;
import com.nsc.customer.validation.AddressValidation;

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
