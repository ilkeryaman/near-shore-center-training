package com.nsc.customer.configuration.resilience.retry;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class NSCRetryConfig {
    @Value("${retry.maxAttempts}")
    private int maxAttempts;

    @Value("${retry.waitDuration}")
    private long waitDuration;

    @Bean
    public RetryConfig getRetryConfig(){
        return RetryConfig.custom()
                .maxAttempts(maxAttempts)
                .waitDuration(Duration.ofMillis(waitDuration))
                .build();
    }

    @Bean
    public RetryRegistry getRetryRegistry(){
        return RetryRegistry.of(getRetryConfig());
    }

    @Bean
    public Retry getRetry(){
        return getRetryRegistry().retry("retryXtimesByWaitingXseconds");
    }
}
