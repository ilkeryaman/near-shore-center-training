package com.nsc.billing.listener.messaging;

public interface IMessageListener {
    void listenCustomerTopics(String topic, String message);
}
