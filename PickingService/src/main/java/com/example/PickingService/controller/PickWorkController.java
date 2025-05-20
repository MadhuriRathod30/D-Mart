package com.example.PickingService.controller;

import com.example.PickingService.exception.OrderNotFoundException;
import com.example.PickingService.service.PickService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

@Slf4j
@RestController
@RequestMapping("/pick")
public class PickWorkController {

    @Autowired
    PickService pickService;


    @GetMapping("/start/{id}")
    public ResponseEntity<String> startPicking(@PathVariable("id") String orderId){

        log.info("starting the pick work for orderId={}",orderId);
        try {
            pickService.pickStart(orderId);

        }catch (OrderNotFoundException onf){
            log.error("Order is not found for orderId={}",orderId,onf);
            throw onf;
        }

        log.info("Pick Work is complete for orderId={}",orderId);
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Order Service is out of service");
    }









}

