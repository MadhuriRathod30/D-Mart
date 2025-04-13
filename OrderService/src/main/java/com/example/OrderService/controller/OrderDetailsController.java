package com.example.OrderService.controller;

import com.example.OrderService.model.OrderDetails;
import com.example.OrderService.model.OrderResponse;
import com.example.OrderService.model.OrderStatus;
import com.example.OrderService.repository.OrderStatusRepository;
import com.example.OrderService.service.OrderDetailsService;
import com.example.OrderService.service.OrderStatusService;
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
    OrderDetailsService orderDetailsService;

    @Autowired
    OrderStatusService orderStatusService;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderDetailsRequestBody request){

        if(request.getCustomerID() == 0 ||request.getItemName()==null ||request.Quantity ==0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing Customer ID or items");
        }



        try{
            OrderResponse response = orderDetailsService.placeOrder(orderDetails);

        }


        OrderStatus status = new OrderStatus();
        status.setOrderID(saveOrder.getOrderId());
        status.setStatusOrd("CREATED");
        status.setDateTime(LocalDateTime.now());

        OrderStatus saveStatus = orderStatusService.UpdateStatus(status);



        return ResponseEntity.status(HttpStatus.CREATED).body(saveOrder);
    }



}
