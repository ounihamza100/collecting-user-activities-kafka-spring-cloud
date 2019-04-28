package com.dashboard;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created by haithem.ben-chaaben on 29/03/2019.
 */
@Configuration
public class ClassConfig {
    @Bean
    public Properties properties() {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "kafka");
        return properties;
    }

    @Bean
    public KafkaConsumer<String, String> kafkaConsumer() {

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties());
        return consumer;
    }

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }
}
