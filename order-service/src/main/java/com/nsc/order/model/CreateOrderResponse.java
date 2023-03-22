package com.nsc.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.XmlType;

@Data
@AllArgsConstructor
@XmlType(name = "CreateOrderResponse")
public class CreateOrderResponse {
    private String code;
    private String message;
}
