package com.example.OrderService.service;

import com.example.OrderService.model.OrderStatus;
import com.example.OrderService.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderStatusService {

    @Autowired
    OrderStatusRepository orderStatusRepository;

    public String getOrderStatus(String OrderId){

        Optional<OrderStatus> order = orderStatusRepository.findById(OrderId);

        return order.map(OrderStatus::toString).orElse("Order not found");
    }
}
