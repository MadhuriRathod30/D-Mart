package com.example.OrderService.controller;

import com.example.OrderService.model.OrderStatus;
import com.example.OrderService.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/order-status")
public class OrderStatusController {

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @PostMapping("/update")
    public ResponseEntity<OrderStatus> changeStatus(@RequestBody OrderStatusRequestBody request){

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderID(request.OrderID);
        orderStatus.setStatusOrd(request.getStatus());
        orderStatus.setDateTime(LocalDateTime.now());

        OrderStatus savedStatus = orderStatusRepository.save(orderStatus);
        return ResponseEntity.ok(savedStatus);

    }




}
