package com.example.OrderService.controller;

import com.example.OrderService.model.OrderStatus;
import com.example.OrderService.repository.OrderStatusRepository;
import com.example.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/status")
public class OrderStatusController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @GetMapping("/{id}")
    public ResponseEntity<String> getStatus(@PathVariable("id") String orderId) {
        String status;
        status = orderService.getOrderStatus(orderId);
        if (status != null) {
            return ResponseEntity.status(HttpStatus.OK).body(status);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order not found");
        }
    }
}
