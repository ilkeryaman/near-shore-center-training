package com.nsc.customer.configuration.resilience.retry;

import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CustomerRetryConfig {
    @Value("${resilience.retry.maxAttempts}")
    private int maxAttempts;

    @Value("${resilience.retry.waitDuration}")
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
}
