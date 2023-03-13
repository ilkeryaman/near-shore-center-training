package com.nsc.customer.repository;

import com.nsc.customer.model.customer.Customer;

import java.util.List;

public interface ICustomerRepository {
    List<Customer> findAll();
    Customer findById(Long id);
    Customer create(Customer customer);
    void update(Customer customer);
    void delete(Long id);
}
