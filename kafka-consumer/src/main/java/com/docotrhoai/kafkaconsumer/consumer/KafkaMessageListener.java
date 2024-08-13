package com.docotrhoai.kafkaconsumer.consumer;


import com.docotrhoai.kafkaconsumer.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageListener {

    @KafkaListener( topics = "doctorhoai3", groupId = "Dth", topicPartitions = { @TopicPartition(topic = ("doctorhoai3"), partitions = {"3"})})
    public void consumer( String message ){
        log.info("Consumer consume with the message : {}", message);
    }

//    @KafkaListener( topics = "doctorhoai3", groupId = "Dth")
//    public void consumerCustomer(Customer customer){
//        log.info("Consumer consume with the message : {}", customer.toString());
//    }
}
