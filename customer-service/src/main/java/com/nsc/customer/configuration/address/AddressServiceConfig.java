package com.nsc.customer.configuration.address;

import com.nsc.customer.service.address.IAddressService;
import com.nsc.customer.service.address.impl.AddressWebClientServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AddressServiceConfig {
    @Bean
    @Primary
    public IAddressService getAddressService(){
        return new AddressWebClientServiceImpl();
    }
}
