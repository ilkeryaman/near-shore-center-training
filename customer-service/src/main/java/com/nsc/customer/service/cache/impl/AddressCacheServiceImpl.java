package com.nsc.customer.service.cache.impl;

import com.nsc.customer.enums.cache.CacheName;
import com.nsc.customer.service.cache.ICacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class AddressCacheServiceImpl implements ICacheService {

    private final String cacheName = CacheName.ADDRESS.getValue();

    @Autowired
    private CacheManager cacheManager;

    @Override
    public <T> T getCache(String key, Class keyClass) {
        return (T) cacheManager.getCache(cacheName).get(key, keyClass);
    }

    @Override
    public <T> void putCache(String key, T data) {
        cacheManager.getCache(cacheName).put(key, data);
    }

    @Override
    public void evictCache(String key) {
        cacheManager.getCache(cacheName).evictIfPresent(key);
    }
}
