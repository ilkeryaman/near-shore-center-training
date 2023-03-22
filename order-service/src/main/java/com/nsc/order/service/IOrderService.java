package com.nsc.order.service;

import com.nsc.order.model.CreateOrderRequest;
import com.nsc.order.model.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getCustomerOrders(Long customerId);
    void createOrder(CreateOrderRequest request);
}
