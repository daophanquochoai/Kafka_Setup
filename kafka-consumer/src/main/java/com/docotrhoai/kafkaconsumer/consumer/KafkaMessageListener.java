package com.docotrhoai.kafkaconsumer.consumer;


import com.docotrhoai.kafkaconsumer.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageListener {

    @KafkaListener( topics = "doctorhoai2", groupId = "Dth")
    public void consumer( String message ){
        log.info("Consumer consume with the message : {}", message);
    }

    @KafkaListener( topics = "doctorhoai2", groupId = "Dth")
    public void consumerCustomer(Customer customer){
        log.info("Consumer consume with the message : {}", customer.toString());
    }
}
