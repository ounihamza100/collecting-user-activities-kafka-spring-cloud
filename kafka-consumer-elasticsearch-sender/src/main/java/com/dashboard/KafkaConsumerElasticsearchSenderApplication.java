package com.dashboard;

import com.dashboard.dao.UserTraceRepository;
import com.dashboard.mapper.UserDetailsMapper;
import com.dashboard.model.UserDetails;
import com.dashboard.model.UserDetailsToElastic;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.Arrays;

@SpringBootApplication
public class KafkaConsumerElasticsearchSenderApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerElasticsearchSenderApplication.class, args);
    }

    @Autowired
    private KafkaConsumer<String, String> kafkaConsumer;
    @Autowired
    private UserTraceRepository userTraceRepository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public void run(String... args) throws Exception {

        String topicName = "userDetails";
        kafkaConsumer.subscribe(Arrays.asList(topicName));

        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> events : records) {
                System.out.println("==============================");

                String aux = events.value().substring(1, events.value().length() - 1).replace("\\", "");
                System.out.println(aux);
                    UserDetails userDetails = mapper.readValue(aux, UserDetails.class);

                    UserDetailsToElastic elastic = UserDetailsMapper.modelToElastic(userDetails);
                    userTraceRepository.save(elastic);
                System.out.println("==============================");
            }
        }
    }
}
