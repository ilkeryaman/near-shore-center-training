package com.nsc.customer.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsc.customer.model.customer.Customer;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CustomerDataHelper {
    public static List<Customer> getListOfCustomer(){
        List<Customer> customerList = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            customerList = objectMapper.readValue(new File("src/test/resources/data/customers.json"), new TypeReference<List<Customer>>() { });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }
}
