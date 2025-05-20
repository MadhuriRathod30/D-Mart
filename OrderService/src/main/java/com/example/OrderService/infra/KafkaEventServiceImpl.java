package com.example.OrderService.infra;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaEventServiceImpl implements EventService{

    @KafkaListener(topics = "pick-events")
    @Override
    public void consumePickCompleteEvent() {

    }
}
