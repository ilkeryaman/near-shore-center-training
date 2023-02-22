package com.nsc.customer.configuration.address;

import com.nsc.customer.enums.cache.CacheKey;
import com.nsc.customer.service.address.IAddressService;
import com.nsc.customer.service.cache.ICacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component("addressPicker")
public class AddressPicker implements CommandLineRunner {

    @Autowired
    ICacheService cacheService;

    @Autowired
    IAddressService addressService;

    @Override
    public void run(String... args) throws Exception {
        getListOfAddresses();
    }

    private void getListOfAddresses(){
        cacheService.putCache(CacheKey.ADDRESS_LIST.getValue(), addressService.getListOfAddresses());
    }
}
