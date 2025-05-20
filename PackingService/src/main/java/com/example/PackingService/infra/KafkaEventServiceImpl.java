package com.example.PackingService.infra;

import com.example.PickingService.model.PickWork;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaEventServiceImpl implements EventService{

    @KafkaListener(topics = )
    @Override
    public void consumePickCompleteEvent(PickWork pickwork) {

    }
}
