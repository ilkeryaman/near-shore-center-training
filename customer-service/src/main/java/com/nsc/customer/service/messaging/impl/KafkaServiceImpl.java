package com.nsc.customer.service.messaging.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsc.customer.exception.MessageNotProcessedErrorException;
import com.nsc.customer.model.messaging.EventMessage;
import com.nsc.customer.service.messaging.IMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaServiceImpl implements IMessageService {
    private Logger logger = LoggerFactory.getLogger(KafkaServiceImpl.class);

    @Value("${spring.kafka.enabled}")
    private boolean kafkaEnabled;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private KafkaTemplate<String, String> messageTemplate;

    @Override
    public void sendMessage(EventMessage eventMessage) {
        if(kafkaEnabled){
            String message = null;
            try {
                message = objectMapper.writeValueAsString(eventMessage.getPayload());
            } catch (JsonProcessingException ex) {
                logger.error(ex.getMessage());
                throw new MessageNotProcessedErrorException();
            }

            ListenableFuture<SendResult<String, String>> result = messageTemplate.send(eventMessage.getTopic(), eventMessage.getKey(), message);
            result.addCallback(new ListenableFutureCallback<>() {
                @Override
                public void onSuccess(SendResult<String, String> result) {
                    logger.info("Sent message=[{}] with offset=[{}] on topic: {}",
                            eventMessage.getPayload(), result.getRecordMetadata().offset(), result.getRecordMetadata().topic());
                }

                @Override
                public void onFailure(Throwable ex) {
                    logger.info("Unable to send message=[{}] due to: {}", eventMessage.getPayload(), ex.getMessage());
                }
            });
        }
    }
}
