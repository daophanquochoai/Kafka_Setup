package com.doctorhoai.kafka_retry.service;

import com.doctorhoai.kafka_retry.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KafkaMessageProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendUser(User user){
        CompletableFuture<SendResult<String,Object>> future =  kafkaTemplate.send("version5", user);
        future.whenComplete( ( result, throwable) -> {
            if( throwable == null ){
                log.info("Sent message = [ {} ] with offset = [ {} ] , partitions = [ {} ]", user.toString(), result.getRecordMetadata().offset(), result.getRecordMetadata().partition());
            }else{
                log.error("Unable to send message = [ {} ] due to : {}", user.toString(), throwable.getMessage());
            }
        } );
    }
}
