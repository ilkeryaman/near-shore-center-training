package com.nsc.customer.service.customer.impl;

import com.nsc.customer.enums.address.City;
import com.nsc.customer.enums.cache.CacheKey;
import com.nsc.customer.enums.response.ResponseMessage;
import com.nsc.customer.exception.AddressNotFoundException;
import com.nsc.customer.service.address.IAddressService;
import com.nsc.customer.service.cache.ICacheService;
import com.nsc.customer.service.customer.ICustomerService;
import com.nsc.customer.model.customer.Address;
import com.nsc.customer.model.customer.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("customerMemoryDBService")
@DependsOn("addressPicker")
public class CustomerMemoryDBServiceImpl implements ICustomerService {
    private final Logger logger = LoggerFactory.getLogger(CustomerMemoryDBServiceImpl.class);

    @Autowired
    private ICacheService cacheService;

    @Autowired
    private IAddressService addressService;

    private List<Customer> listOfCustomers;

    CustomerMemoryDBServiceImpl(){
        listOfCustomers = new ArrayList<>();
    }

    @PostConstruct
    private void initData(){
        Address defaultAddress = getDefaultAddress();
        List<Address> listOfAddresses = cacheService.getCache(CacheKey.ADDRESS_LIST.getValue(), List.class);
        Optional<Address> addressOptionalOfCustomer1 = listOfAddresses.stream().filter(address -> address.getCity().equals(City.ISTANBUL.getValue())).findFirst();
        Optional<Address> addressOptionalOfCustomer2 = listOfAddresses.stream().filter(address -> address.getCity().equals(City.IZMIR.getValue())).findAny();

        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setCustomerNo("1001");
        customer1.setName("Ilker");
        customer1.setSurname("Yaman");
        customer1.setAddress(addressOptionalOfCustomer1.orElse(defaultAddress));

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setCustomerNo("1002");
        customer2.setName("Sinan");
        customer2.setSurname("Bulubay");
        customer2.setAddress(addressOptionalOfCustomer2.orElse(defaultAddress));

        listOfCustomers.add(customer1);
        listOfCustomers.add(customer2);
    }

    public List<Customer> getCustomerList(){
        return listOfCustomers;
    }

    @Override
    public Customer findCustomerById(long id) {
        Optional<Customer> customerOptional = listOfCustomers.stream().filter(customer -> customer.getId() == id).findFirst();
        return customerOptional.isPresent() ? customerOptional.get() : null;
    }

    @Override
    public boolean addCustomer(Customer customer) {
        validateAddress(customer.getAddress());
        listOfCustomers.add(customer);
        return true;
    }

    @Override
    public boolean deleteCustomer(long id) {
        return listOfCustomers.removeIf(customer -> customer.getId() == id);
    }

    private void validateAddress(Address address){
        List<Address> listOfAddresses = cacheService.getCache(CacheKey.ADDRESS_LIST.getValue(), List.class);
        if(listOfAddresses == null){
            listOfAddresses = addressService.getListOfAddresses();
            cacheService.putCache(CacheKey.ADDRESS_LIST.getValue(), listOfAddresses);
            logger.warn("Address was null!");
        }

        if(listOfAddresses.stream().noneMatch(addr ->
                addr.getCity().equals(address.getCity())
                        && addr.getDistrict().equals(address.getDistrict()))
        ){
            throw new AddressNotFoundException(ResponseMessage.ADDRESS_NOT_FOUND.getValue());
        }
    }

    private Address getDefaultAddress(){
        return new Address(City.ANKARA.getValue(), "Çankaya");
    }
}
