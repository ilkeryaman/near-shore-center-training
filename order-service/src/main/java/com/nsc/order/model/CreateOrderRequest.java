package com.nsc.order.model;

import lombok.Data;

@Data
public class CreateOrderRequest {
    private Order order;
    private CreditCard creditCard;
}
