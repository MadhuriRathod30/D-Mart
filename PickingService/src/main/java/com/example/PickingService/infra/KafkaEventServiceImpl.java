package com.example.PickingService.infra;

import com.example.PickingService.model.PickWork;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class KafkaEventServiceImpl implements EventService {

    @Autowired
    KafkaTemplate<String, PickWork> kafkaTemplate;

    @Value("${spring.kafka.topic.picking.started}")
    public String topic;


    @Override
    public void sendPickCompleteEvent(PickWork pickWork) {
        log.info("picking work is completed for pickWork={}",pickWork);
       try{
           kafkaTemplate.send(topic, pickWork);
       }catch(Exception ex){
           log.error("Error aya re");
       }
    }
}
