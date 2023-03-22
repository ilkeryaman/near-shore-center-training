package com.nsc.order.model;

import lombok.Data;

@Data
public class CreditCard {
    private String name;
    private Integer month;
    private Integer year;
    private String cvv;
}
