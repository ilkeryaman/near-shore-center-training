package com.nsc.order.service.impl;

import com.nsc.order.model.CreateOrderRequest;
import com.nsc.order.model.Order;
import com.nsc.order.service.IOrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IOrderService {
    private List<Order> listOfOrders;

    OrderServiceImpl(){
        listOfOrders = new ArrayList<>();
    }

    @Override
    public List<Order> getCustomerOrders(Long customerId) {
        return listOfOrders.stream().filter(order -> order.getCustomerId() == customerId).collect(Collectors.toList());
    }

    @Override
    public void createOrder(CreateOrderRequest request) {
        listOfOrders.add(request.getOrder());
    }
}
