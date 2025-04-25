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
    public ResponseEntity<ResponseBody> createOrder(@RequestBody OrderDetailsRequestBody request){

        OrderDetails orderDetails = new OrderDetails();

        orderDetails.setQuantity(request.getQuantity());
        orderDetails.setItemName(request.getItemName());
        orderDetails.setCreatedAt(LocalDateTime.now());
        orderDetails.setCustomerId(request.getCustomerID());

        OrderDetails orderDetails1= orderDetailsRepository.save(orderDetails);


        OrderStatus orderStatus = new OrderStatus();

        orderStatus.setOrderID(orderDetails.getOrderId());
        orderStatus.setStatus("CREATED");
        orderStatus.setDateTime(orderDetails.getCreatedAt());

        orderStatusRepository.save(orderStatus);



        ResponseBody responseBody = new ResponseBody();
        responseBody.setCustomerID(orderDetails1.getCustomerId());
        responseBody.setQuantity(orderDetails1.getQuantity());
        responseBody.setItemName(orderDetails1.getItemName());
        return  ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }



}
