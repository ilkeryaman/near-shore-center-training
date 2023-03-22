package com.nsc.order;

import com.nsc.order.controller.impl.OrderControllerImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.ws.Endpoint;

@SpringBootApplication
public class OrderServiceApplication implements CommandLineRunner {
    @Value("${soap.bottomup.url}")
    private String url;

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Endpoint.publish(url, new OrderControllerImpl());
    }
}