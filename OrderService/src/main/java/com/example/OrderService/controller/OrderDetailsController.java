package com.example.OrderService.controller;

import com.example.OrderService.model.OrderDetails;
import com.example.OrderService.repository.OrderDetailsRepository;
import com.example.OrderService.service.OrderDetailsService;
import com.example.OrderService.service.OrderStatusService;
import com.example.OrderService.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderDetailsController {

    @Autowired
    OrderDetailsRepository orderDetailsRepository;


    @Autowired
    OrderDetailsService orderDetailsService;

    @Autowired
    Producer producer;

    @GetMapping("/allOrder")
    public List<OrderDetails> getAllOrder(){

        List<OrderDetails> ls  = new ArrayList<>();
        ls = orderDetailsRepository.findAll();

        return ls;

    }

    @Autowired
    OrderStatusService orderStatusService;

    @PostMapping("/create")
    public ResponseEntity<ResponseBody> createOrder(@RequestBody OrderDetails request){

        ResponseBody responseBody = orderDetailsService.createOrder(request);

        producer.MessageToKafkaTopic("Order is created with orderID" + request.getOrderId());

        orderStatusService.setOrderStatus(request);

        return  ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }



}
