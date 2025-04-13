package com.example.OrderService.service;

import com.example.OrderService.controller.OrderDetailsRequestBody;
import com.example.OrderService.model.OrderDetails;
import com.example.OrderService.model.OrderResponse;
import com.example.OrderService.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Override
    public OrderResponse placeOrder(OrderDetailsRequestBody request) {
        //Data Mapping to model
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setItemName(request.getItemName());
        orderDetails.setCreatedAt(LocalDateTime.now());
        orderDetails.setQuantity(request.getQuantity());
        orderDetails.setCustomerId(request.getCustomerID());

        OrderDetails savedOrder = orderDetailsRepository.save(orderDetails);

          return new OrderResponse("sucessfully created order for order ID:" + savedOrder.getOrderId());
    }
}
