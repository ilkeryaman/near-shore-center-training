package com.nsc.customer.controller;

import com.nsc.customer.resilience.ResilienceConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/resilience")
public class ResilienceController {

    @Autowired
    private ResilienceConfigurer resilienceConfigurer;

    @GetMapping("/circuitbreaker")
    public ResponseEntity<Map> getCircuitBreakers(){
        return ResponseEntity.status(HttpStatus.OK).body(resilienceConfigurer.getCircuitBreakerMap());
    }

    @GetMapping("/retry")
    public ResponseEntity<Map> getRetries(){
        return ResponseEntity.status(HttpStatus.OK).body(resilienceConfigurer.getRetryMap());
    }
}
