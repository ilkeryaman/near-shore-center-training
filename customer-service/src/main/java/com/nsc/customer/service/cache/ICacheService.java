package com.nsc.customer.service.cache;

import java.util.List;

public interface ICacheService {
    <T> T getCache(String key, Class keyClass);
    <T> void putCache(String key, T data);
    void evictCache(String key);
}
