package com.nsc.customer.service.customer.impl;

import com.nsc.customer.enums.messaging.KafkaTopic;
import com.nsc.customer.model.customer.Customer;
import com.nsc.customer.model.messaging.EventMessage;
import com.nsc.customer.repository.ICustomerRepository;
import com.nsc.customer.service.customer.ICustomerService;
import com.nsc.customer.service.messaging.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("customerDBService")
public class CustomerDBServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IMessageService messageService;

    @Override
    public List<Customer> getCustomerList() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerById(long id) {
        return customerRepository.findById(id);
    }

    @Override
    @Transactional
    public boolean addCustomer(Customer customer) {
        Customer customerCreated = customerRepository.create(customer);
        messageService.sendMessage(new EventMessage(String.valueOf(customer.getId()), KafkaTopic.NSC_CUSTOMER_CREATED.getValue(), customerCreated));
        return true;
    }

    @Override
    @Transactional
    public boolean deleteCustomer(long id) {
        customerRepository.delete(id);
        return true;
    }
}
