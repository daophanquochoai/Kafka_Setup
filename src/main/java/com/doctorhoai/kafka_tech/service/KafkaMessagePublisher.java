package com.doctorhoai.kafka_tech.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
@Slf4j
public class KafkaMessagePublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessageToTopic( String message ){
        CompletableFuture<SendResult<String,Object>> future =  kafkaTemplate.send("doctorhoai1", message);
        future.whenComplete( ( result, throwable) -> {
            if( throwable == null ){
                log.info("Sent message = [ {} ] with offset = [ {} ] , partitions = [ {} ]", message, result.getRecordMetadata().offset(), result.getRecordMetadata().partition());
            }else{
                log.error("Unable to send message = [ {} ] due to : {}", message, throwable.getMessage());
            }
        } );
    }

}
