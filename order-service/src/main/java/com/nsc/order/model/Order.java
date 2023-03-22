package com.nsc.order.model;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private Long customerId;
    private Long MovieId;
}
