package com.nsc.customer.model.customer;

import com.nsc.customer.validation.AddressValidation;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Min(value = 1L, message = "id can not be lower than 1!")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSequenceGenerator")
    @SequenceGenerator(name = "customerSequenceGenerator", sequenceName = "customer_sequence", initialValue = 100)
    private long id;

    @NotBlank(message = "customerNo can not be null or blank!")
    @Column
    private String customerNo;

    @NotBlank(message = "name can not be null or blank!")
    @Column
    private String name;

    @NotBlank(message = "surname can not be null or blank!")
    @Column
    private String surname;

    @Column
    private String segment;

    @AddressValidation
    @NotNull(message = "address can not be null!")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;
}
