package com.nsc.billing.model.account;

import com.nsc.billing.model.address.Address;
import com.nsc.billing.model.address.converter.AddressConverter;
import com.nsc.billing.model.customer.Customer;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "customerAccount")
public class CustomerAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Convert(converter = AddressConverter.class)
    @Column(name = "address_id")
    private Address address;
}
