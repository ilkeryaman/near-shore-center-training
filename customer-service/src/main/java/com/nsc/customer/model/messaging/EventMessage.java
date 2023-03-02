package com.nsc.customer.model.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventMessage {
    private String key;
    private String topic;
    private Object payload;
}
