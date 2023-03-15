package com.nsc.customer.resilience;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import io.vavr.CheckedFunction0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResilienceConfigurer {

    @Autowired
    private RetryRegistry retryRegistry;

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    private Map<String, Retry> retryMap;
    private Map<String, CircuitBreaker> circuitBreakerMap;

    ResilienceConfigurer(){
        retryMap = new HashMap<>();
        circuitBreakerMap = new HashMap<>();
    }

    public <T> CheckedFunction0<T> decorateWithRetryAndCircuitBreaker(String instanceName, CheckedFunction0<T> methodReference){
        Retry retry = retryRegistry.retry(instanceName);
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(instanceName);
        CheckedFunction0<T> checkedFunction0 = CircuitBreaker.decorateCheckedSupplier(circuitBreaker, methodReference);
        checkedFunction0 = Retry.decorateCheckedSupplier(retry, checkedFunction0);
        retryMap.put(instanceName, retry);
        circuitBreakerMap.put(instanceName, circuitBreaker);
        return checkedFunction0;
    }

    public Map<String, Retry> getRetryMap(){
        return retryMap;
    }

    public Map<String, CircuitBreaker> getCircuitBreakerMap(){
        return circuitBreakerMap;
    }

}
