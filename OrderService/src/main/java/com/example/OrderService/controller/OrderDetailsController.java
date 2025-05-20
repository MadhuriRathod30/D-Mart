package com.example.OrderService.controller;

import com.example.OrderService.exception.EntityNotFoundException;
import com.example.OrderService.model.OrderDetails;
import com.example.OrderService.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderDetailsController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody OrderDetails request) {
        log.info("Creating the order request={}", request);
        OrderDetails orderDetails = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderEntity(@PathVariable("id") String orderId) {
        OrderDetails response;
        try {
            response = orderService.getOrderEntity(orderId);
        } catch (EntityNotFoundException entityNotFoundException) {
            log.error("Could not find entity for orderId={}", orderId);
            return new ResponseEntity<>(entityNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
