package com.example.OrderService.service;

import org.springframework.kafka.annotation.KafkaListener;

@KafkaListener
public class Consumer {

    public void ListenToTopic(String receivedMsg){

        System.out.println("This is a msg received form Kafka Topic " + receivedMsg);

    }
}
