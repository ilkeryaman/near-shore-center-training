package com.nsc.customer.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsc.customer.model.customer.Customer;
import com.nsc.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service("customerFileService")
public class CustomerFileServiceImpl implements ICustomerService {

    private List<Customer> listOfCustomer;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${customer.file.url}")
    private String customerFileUrl;

    @PostConstruct
    private void initData() {
        try {
            listOfCustomer = objectMapper.readValue(new File(customerFileUrl), new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getCustomerList() {
        return listOfCustomer;
    }

    @Override
    public Customer findCustomerById(long id) {
        Optional<Customer> customerOptional = listOfCustomer.stream().filter(customer -> customer.getId() == id).findFirst();
        return customerOptional.isPresent() ? customerOptional.get() : null;
    }

    @Override
    public boolean addCustomer(Customer customer) {
        listOfCustomer.add(customer);
        return true;
    }

    @Override
    public boolean deleteCustomer(long id) {
        return listOfCustomer.removeIf(customer -> customer.getId() == id);
    }
}
