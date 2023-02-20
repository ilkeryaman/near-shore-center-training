package com.nsc.customer.service.impl;

import com.nsc.customer.model.customer.Address;
import com.nsc.customer.model.customer.Customer;
import com.nsc.customer.service.ICustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("customerMemoryDBService")
public class CustomerMemoryDBServiceImpl implements ICustomerService {

    private List<Customer> listOfCustomer;

    CustomerMemoryDBServiceImpl(){
        listOfCustomer = new ArrayList<>();
    }

    @PostConstruct
    private void initData(){
        Address addressOfCustomer1 = new Address();
        addressOfCustomer1.setCity("İstanbul");
        addressOfCustomer1.setDistrict("Bahcelievler");

        Address addressOfCustomer2 = new Address();
        addressOfCustomer2.setCity("Ankara");
        addressOfCustomer2.setDistrict("Cankaya");

        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setCustomerNo("1001");
        customer1.setName("Ilker");
        customer1.setSurname("Yaman");
        customer1.setAddress(addressOfCustomer1);

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setCustomerNo("1002");
        customer2.setName("Sinan");
        customer2.setSurname("Bulubay");
        customer2.setAddress(addressOfCustomer2);

        listOfCustomer.add(customer1);
        listOfCustomer.add(customer2);
    }

    public List<Customer> getCustomerList(){
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
