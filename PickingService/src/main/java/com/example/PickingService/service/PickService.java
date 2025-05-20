package com.example.PickingService.service;

import com.example.PickingService.exception.ExternalServiceUnavailableException;
import com.example.PickingService.exception.OrderNotFoundException;
import com.example.PickingService.infra.EventService;
import com.example.PickingService.infra.KafkaEventServiceImpl;
import com.example.PickingService.model.PickWork;
import com.example.PickingService.web.ExternalAssociateService;
import com.example.PickingService.web.ExternalOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class PickService {

    @Autowired
    ExternalAssociateService externalAssociateService;

    @Autowired
    ExternalOrderService externalOrderService;

    @Autowired
    KafkaEventServiceImpl kafkaEventService;

    @Autowired
    EventService eventService;

    public void pickStart(String orderId) {
        int orderQuantity = 0;

        try {
            orderQuantity = externalOrderService.getOrderQuantity(orderId);
            log.info("Order quantity to be picked is={}",orderQuantity);
            Long associateId = externalAssociateService.assignAssociate(orderId);
            log.info("Associate is assigned to pick the order with associateId={}",associateId);
            PickWork pickWork = buildPickWork(orderId, orderQuantity, associateId);
            log.info("Pick Work info is={}",pickWork);
            kafkaEventService.sendPickCompleteEvent(pickWork);
            log.info("pickWork complete event is produced in kafka topic");
            externalAssociateService.unAssignAssociate(associateId);
            log.info("Pick Work is complete and associate is unassigned from it with associateId={}",associateId);
        } catch (OrderNotFoundException | ExternalServiceUnavailableException onf) {
            log.error("pick work has failed");
            throw onf;
        }
    }

    private PickWork buildPickWork(String orderId, int orderQuantity, Long associateId ) {
        log.info("Creating a pickWork event");
        PickWork pickWork = new PickWork();
        pickWork.setOrderQuantity(orderQuantity);
        pickWork.setOrderID(orderId);
        pickWork.setAssociateID(associateId);
        pickWork.setPickStartTime(LocalDateTime.now());
        return pickWork;
    }
}
