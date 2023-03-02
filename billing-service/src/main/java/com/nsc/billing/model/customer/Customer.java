package com.nsc.billing.model.customer;

import com.nsc.billing.model.address.Address;
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
