package com.nsc.address.configuration.messaging;

import com.nsc.address.enums.messaging.KafkaTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "spring.kafka", name = "enabled", matchIfMissing = true)
public class KafkaTopicConfig {
    @Bean
    public String[] getTopics() {
        return new String[]{
                KafkaTopic.NSC_CUSTOMER_CREATED.getValue()
        };
    }
}
