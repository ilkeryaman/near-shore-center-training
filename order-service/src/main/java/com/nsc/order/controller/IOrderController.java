package com.nsc.order.controller;

import com.nsc.order.model.CreateOrderRequest;
import com.nsc.order.model.CreateOrderResponse;
import com.nsc.order.model.GetOrderListResponse;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IOrderController {
    @WebMethod
    CreateOrderResponse createOrder(CreateOrderRequest request);

    @WebMethod
    GetOrderListResponse getOrderList(Long customerId);
}
