package com.nsc.customer.model.customer;

import com.nsc.customer.validation.AddressValidation;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Customer {
    @Min(value = 1L, message = "id can not be lower than 1!")
    private long id;
    @NotBlank(message = "customerNo can not be null or blank!")
    private String customerNo;
    @NotBlank(message = "name can not be null or blank!")
    private String name;
    @NotBlank(message = "surname can not be null or blank!")
    private String surname;
    private String segment;
    @AddressValidation
    @NotNull(message = "address can not be null!")
    private Address address;
}
