package com.doctorhoai.kafka_tech.controller;

import com.doctorhoai.kafka_tech.service.KafkaMessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("producer-app")
@RequiredArgsConstructor
@Slf4j
public class EventController {
    private final KafkaMessagePublisher publisher;

    @GetMapping("/pulish/{message}")
    public ResponseEntity<?> publishMessage(
            @PathVariable String message
    ){
        try{
            for( int i = 1 ; i <= 10000 ; i++ ){
                publisher.sendMessageToTopic(message + " : " + i);
            }
            return ResponseEntity.ok("Message pulished successful");
        }catch (Exception ex ){
            log.error("Message pulished failure");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failute");
        }
    }
}
