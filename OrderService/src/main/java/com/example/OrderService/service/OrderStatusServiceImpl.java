package com.example.OrderService.service;

import com.example.OrderService.model.OrderStatus;
import com.example.OrderService.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderStatusServiceImpl implements OrderStatusService{

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Override
    public OrderStatus UpdateStatus(OrderStatus orderStatus) {

        return orderStatusRepository.save(orderStatus);
    }
}
