package com.nsc.customer.scheduledjob;

import com.nsc.customer.enums.cache.CacheKey;
import com.nsc.customer.service.cache.ICacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheCleanJob {
    private final Logger logger = LoggerFactory.getLogger(CacheCleanJob.class);

    @Autowired
    private ICacheService cacheService;

    @Scheduled(fixedDelayString = "${cache.clean.intervalInMilliseconds}", initialDelayString = "${cache.clean.intervalInMilliseconds}")
    public void cleanCache(){
        cacheService.evictCache(CacheKey.ADDRESS_LIST.getValue());
        logger.debug("Clean cache is triggered!");
        logger.info("I have evicted the cache!");
    }
}
