package com.example.OrderService.controller;

import com.example.OrderService.model.OrderStatus;
import com.example.OrderService.repository.OrderStatusRepository;
import com.example.OrderService.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order-status")
public class OrderStatusController {

    @Autowired
    OrderStatusService orderStatusService;

    @Autowired
    OrderStatusRepository orderStatusRepository;


    @GetMapping("/get")
    public ResponseEntity<String> getStatus(@RequestParam("order_id") String orderId){

        String status;

        status = orderStatusService.getOrderStatus(orderId);

        if(status != null) {
            return ResponseEntity.status(HttpStatus.OK).body(status);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order not found");
        }
    }

    @GetMapping("/allOrder")
    public List<OrderStatus> getAllOrderStatus(){
        List<OrderStatus> ls = new ArrayList<>();

        ls = orderStatusRepository.findAll();

        return ls;
    }




}
