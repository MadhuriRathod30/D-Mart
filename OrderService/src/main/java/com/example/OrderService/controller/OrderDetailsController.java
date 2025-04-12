package com.example.OrderService.controller;

import com.example.OrderService.model.OrderDetails;
import com.example.OrderService.model.OrderStatus;
import com.example.OrderService.repository.OrderDetailsRepository;
import com.example.OrderService.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/order")
public class OrderDetailsController {

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @PostMapping("/create")
    public ResponseEntity<OrderDetails> createOrder(@RequestBody OrderDetailsRequestBody request){

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setItemName(request.getItemName());
        orderDetails.setCreatedAt(LocalDateTime.now());
        orderDetails.setQuantity(request.Quantity);
        orderDetails.setCustomerId(request.CustomerID);

        OrderDetails saveOrder = orderDetailsRepository.save(orderDetails);

        OrderStatus status = new OrderStatus();
        status.setOrderID(saveOrder.getOrderId());
        status.setStatusOrd("CREATED");
        status.setDateTime(LocalDateTime.now());

        OrderStatus saveStatus = orderStatusRepository.save(status);

        System.out.print("save order :" + saveOrder);
        System.out.print("save status :" + saveStatus);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveOrder);
    }



}
