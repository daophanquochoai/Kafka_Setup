package com.doctorhoai.kafka_retry.consumer;

import com.doctorhoai.kafka_retry.dto.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class KafkaMessageConsumer {
    @Autowired
    private ObjectMapper objectMapper;

//    @RetryableTopic(attempts = "4", backoff = @Backoff( delay = 3000, multiplier = 1.5, maxDelay = 15000)) // set thoi gian thu lai, cap so nhan, toi da
    @RetryableTopic(attempts = "10", exclude = { NullPointerException.class }) // khong thu lai khi loi nay
    @KafkaListener(topics = "version5", groupId = "Dth")
    public void consumer(User user) {
        try {
            log.info("Received : {} from {} offset {}", objectMapper.writeValueAsString(user));
            List<String> restrictedIpList = Stream.of("322.241.244.236", "15.55.49.154").collect(Collectors.toList());
            if (restrictedIpList.contains(user.getIpAddress())) {
                throw new RuntimeException("Invalid IP Address received!");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @DltHandler
    public void listenDLT(User user, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.OFFSET) long offset) {
        log.info("DLT Received : {} from topic {} at offset {}", user.getIpAddress(), topic, offset);
    }
}
