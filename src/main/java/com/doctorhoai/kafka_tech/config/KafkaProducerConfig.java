package com.doctorhoai.kafka_tech.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public NewTopic newTopic(){
        NewTopic doctorhoai1 = new NewTopic("doctorhoai1", 3, (short) 1);
        return doctorhoai1;
    }
}
