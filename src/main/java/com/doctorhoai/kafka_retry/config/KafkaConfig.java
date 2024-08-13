package com.doctorhoai.kafka_retry.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic newTopic(){
        NewTopic doctorhoai1 = new NewTopic("version5", 3, (short) 1);
        return doctorhoai1;
    }
}
