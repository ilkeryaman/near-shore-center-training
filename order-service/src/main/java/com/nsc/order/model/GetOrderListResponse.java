package com.nsc.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.XmlType;
import java.util.List;

@Data
@AllArgsConstructor
@XmlType(name = "GetOrderListResponse")
public class GetOrderListResponse {
    private List<Order> order;
}
