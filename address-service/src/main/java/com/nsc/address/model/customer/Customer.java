package com.nsc.address.model.customer;

import com.nsc.address.model.address.Address;
import lombok.Data;

@Data
public class Customer {
    private long id;
    private String customerNo;
    private String name;
    private String surname;
    private String segment;
    private Address address;
}
