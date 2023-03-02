package com.nsc.address.listener.messaging.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsc.address.configuration.messaging.KafkaTopicConfig;
import com.nsc.address.enums.messaging.KafkaTopic;
import com.nsc.address.listener.messaging.IMessageListener;
import com.nsc.address.model.customer.Customer;
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
            String city = customer.getAddress().getCity();
            String district = customer.getAddress().getDistrict();
            logger.info("There is a new customer at {}/{}.", district, city);
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
