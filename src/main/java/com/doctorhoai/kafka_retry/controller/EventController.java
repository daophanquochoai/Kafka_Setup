package com.doctorhoai.kafka_retry.controller;

import com.doctorhoai.kafka_retry.dto.User;
import com.doctorhoai.kafka_retry.service.KafkaMessageProducer;
import com.doctorhoai.kafka_retry.util.CsvReaderUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class EventController {

    @Autowired
    private KafkaMessageProducer publisher;
    @PostMapping("/publish/user")
    public ResponseEntity<?> publishMessage(

    ){
        try{
            List<User> users = CsvReaderUtils.readDataFromCsv();
            log.info("{}", users);
            users.forEach(
                    user -> {
                        publisher.sendUser(user);
                    }
            );
            return ResponseEntity.ok("Message pulished successful");
        }catch (Exception ex ){
            log.error("Message pulished failure : {}" , ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failute");
        }
    }
}
