package com.nsc.customer.configuration.address;

import com.nsc.customer.enums.cache.CacheKey;
import com.nsc.customer.model.customer.Address;
import com.nsc.customer.service.address.IAddressService;
import com.nsc.customer.service.cache.ICacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component("addressPicker")
public class AddressPicker {
    Logger logger = LoggerFactory.getLogger(AddressPicker.class);

    @Autowired
    private ICacheService cacheService;

    @Autowired
    private IAddressService addressService;

    @PostConstruct
    private void getListOfAddresses(){
        cacheService.putCache(CacheKey.ADDRESS_LIST.getValue(), retrieveAddress());
    }

    private List<Address> retrieveAddress(){
        List<Address> listOfAddresses;
        try{
            listOfAddresses = addressService.getListOfAddresses();
        } catch (Exception ex){
            listOfAddresses = new ArrayList<>();
            logger.error("An error occured while retrieving address from address-api!");
        }
        return listOfAddresses;
    }
}
