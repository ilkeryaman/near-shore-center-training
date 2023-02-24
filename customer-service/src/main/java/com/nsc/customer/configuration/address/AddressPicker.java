package com.nsc.customer.configuration.address;

import com.nsc.customer.enums.cache.CacheKey;
import com.nsc.customer.service.address.IAddressService;
import com.nsc.customer.service.cache.ICacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("addressPicker")
public class AddressPicker {

    @Autowired
    private ICacheService cacheService;

    @Autowired
    private IAddressService addressService;

    @PostConstruct
    private void getListOfAddresses(){
        cacheService.putCache(CacheKey.ADDRESS_LIST.getValue(), addressService.getListOfAddresses());
    }
}
