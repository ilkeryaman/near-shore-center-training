package com.nsc.billing.listener.messaging.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsc.billing.configuration.messaging.KafkaTopicConfig;
import com.nsc.billing.enums.messaging.KafkaTopic;
import com.nsc.billing.listener.messaging.IMessageListener;
import com.nsc.billing.model.customer.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@ConditionalOnProperty(prefix = "spring.kafka", name = "enabled", matchIfMissing = true)
public class MessageListenerImpl implements IMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(MessageListenerImpl.class);

    @Autowired
    private KafkaTopicConfig kafkaTopicConfig;


    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "#{kafkaTopicConfig.getTopics()}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenCustomerTopics(@Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Payload String message) {
        Customer customer = deSerializeMessage(message);

        if(KafkaTopic.NSC_CUSTOMER_CREATED.getValue().equals(topic)){
            logger.info("Customer billing account is created for customer with number: {}!", customer.getCustomerNo());
        }

        logger.info("Topic: [{}], Received message: {}", topic, message);
    }

    private Customer deSerializeMessage(String message){
        Customer customer = null;
        if(StringUtils.hasLength(message)){
            try {
                customer = objectMapper.readValue(message, Customer.class);
            } catch (JsonProcessingException ex) {
                logger.error("There is a processing exception, be careful!");
            }
        }
        return customer;
    }
}
