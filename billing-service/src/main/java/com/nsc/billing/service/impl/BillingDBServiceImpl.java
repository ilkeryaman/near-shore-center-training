package com.nsc.billing.service.impl;

import com.nsc.billing.model.account.CustomerAccount;
import com.nsc.billing.model.address.Address;
import com.nsc.billing.model.customer.Customer;
import com.nsc.billing.repository.ICustomerAccountRepository;
import com.nsc.billing.service.IBillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingDBServiceImpl implements IBillingService {
    @Autowired
    private ICustomerAccountRepository customerAccountRepository;

    @Override
    public void addCustomerAccount(Customer customer) {
        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setCustomer(customer);
        customerAccount.setAddress(customer.getAddress());
        customerAccountRepository.save(customerAccount);
    }

    @Override
    public List<CustomerAccount> findByAddressId(Long addressId) {
        Address address = new Address();
        address.setId(addressId);
        return customerAccountRepository.findByAddress(address);
    }

    @Override
    public List<CustomerAccount> findByCustomerNo(String customerNo) {
        return customerAccountRepository.findByCustomerNo(customerNo);
    }
}
