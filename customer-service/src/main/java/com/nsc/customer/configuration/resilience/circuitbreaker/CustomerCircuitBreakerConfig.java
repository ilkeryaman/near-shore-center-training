package com.nsc.customer.configuration.resilience.circuitbreaker;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CustomerCircuitBreakerConfig {

    @Value("${resilience.circuitbreaker.failureRateThreshold}")
    private int failureRateThreshold;

    @Value("${resilience.circuitbreaker.waitDurationInOpenState}")
    private long waitDurationInOpenState;

    @Value("${resilience.circuitbreaker.permittedNumberOfCallsInHalfOpenState}")
    private int permittedNumberOfCallsInHalfOpenState;

    @Value("${resilience.circuitbreaker.minimumNumberOfCalls}")
    private int minimumNumberOfCalls;

    @Value("${resilience.circuitbreaker.slidingWindowSize}")
    private int slidingWindowSize;

    @Bean
    public CircuitBreakerConfig getCircuitBreakerConfig(){
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(failureRateThreshold)
                .waitDurationInOpenState(Duration.ofMillis(waitDurationInOpenState))
                .permittedNumberOfCallsInHalfOpenState(permittedNumberOfCallsInHalfOpenState)
                .minimumNumberOfCalls(minimumNumberOfCalls)
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(slidingWindowSize)
                .build();
    }

    @Bean
    public CircuitBreakerRegistry getCircuitBreakerRegistry(CircuitBreakerConfig circuitBreakerConfig){
        return CircuitBreakerRegistry.of(circuitBreakerConfig);
    }
}
