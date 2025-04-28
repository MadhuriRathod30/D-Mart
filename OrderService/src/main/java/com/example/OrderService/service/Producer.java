package com.example.OrderService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class Producer {


    @Autowired
    KafkaTemplate<String , String > kafkaTemplate;

    private final String topic = "order_status_topic";

    public void  MessageToKafkaTopic(String message){
        kafkaTemplate.send(topic  , message);
    }




}
