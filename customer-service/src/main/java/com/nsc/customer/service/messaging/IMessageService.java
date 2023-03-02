package com.nsc.customer.service.messaging;

import com.nsc.customer.model.messaging.EventMessage;

public interface IMessageService {
    void sendMessage(EventMessage eventMessage);
}
