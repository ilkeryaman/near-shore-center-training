package com.nsc.billing.model.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsc.billing.model.address.Address;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Data
@Entity(name = "customer")
public class Customer {
    @Id
    private Long id;
    private String customerNo;
    private String name;
    private String surname;
    @Transient
    @JsonIgnore
    private String segment;
    @Transient
    private Address address;
}
