package com.nsc.billing.service;

import com.nsc.billing.model.account.CustomerAccount;
import com.nsc.billing.model.customer.Customer;

import java.util.List;

public interface IBillingService {
    void addCustomerAccount(Customer customer);
    List<CustomerAccount> findByAddressId(Long addressId);
    List<CustomerAccount> findByCustomerNo(String customerNo);
}
