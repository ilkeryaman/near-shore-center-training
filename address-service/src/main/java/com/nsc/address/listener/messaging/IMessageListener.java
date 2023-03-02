package com.nsc.address.listener.messaging;

public interface IMessageListener {
    void listenCustomerTopics(String topic, String message);
}
