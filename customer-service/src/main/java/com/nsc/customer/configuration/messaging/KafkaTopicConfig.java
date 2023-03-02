package com.nsc.customer.configuration.messaging;

import com.nsc.customer.enums.messaging.KafkaTopic;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConditionalOnProperty(prefix = "spring.kafka", name = "enabled", matchIfMissing = true)
public class KafkaTopicConfig {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic customerCreatedTopic() {
        return new NewTopic(KafkaTopic.NSC_CUSTOMER_CREATED.getValue(), 1, (short) 1);
    }

    @Bean
    public String[] getTopics() {
        return new String[]{
                KafkaTopic.NSC_CUSTOMER_CREATED.getValue()
        };
    }
}
