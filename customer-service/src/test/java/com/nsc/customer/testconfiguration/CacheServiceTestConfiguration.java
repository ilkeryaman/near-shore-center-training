package com.nsc.customer.testconfiguration;

import com.nsc.customer.helper.AddressDataHelper;
import com.nsc.customer.service.cache.ICacheService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class CacheServiceTestConfiguration {
    @Bean
    @Primary
    public ICacheService getCacheService(){
        return new ICacheService() {
            @Override
            public <T> T getCache(String key, Class keyClass) {
                return (T) AddressDataHelper.getListOfAddresses();
            }

            @Override
            public <T> void putCache(String key, T data) {

            }

            @Override
            public void evictCache(String key) {

            }
        };
    }
}
