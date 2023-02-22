package com.nsc.customer.service.customer;

import com.nsc.customer.model.customer.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> getCustomerList();
    Customer findCustomerById(long id);
    boolean addCustomer(Customer customer);
    boolean deleteCustomer(long id);
}
