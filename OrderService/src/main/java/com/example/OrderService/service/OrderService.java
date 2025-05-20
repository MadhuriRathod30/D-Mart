package com.example.OrderService.service;

import com.example.OrderService.exception.EntityNotFoundException;
import com.example.OrderService.model.OrderDetails;
import com.example.OrderService.model.OrderStatus;
import com.example.OrderService.repository.OrderDetailsRepository;
import com.example.OrderService.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderStatusRepository orderStatusRepository;


    @Autowired
    OrderDetailsRepository orderDetailsRepository;


    public OrderDetails createOrder(OrderDetails request){
        OrderDetails orderDetails = getOrderDetails(request);
        orderDetails = orderDetailsRepository.save(orderDetails);
        setOrderStatus(request);
        return orderDetails;
    }

    private static OrderDetails getOrderDetails(OrderDetails request) {
        OrderDetails orderDetails = new OrderDetails();

        orderDetails.setQuantity(request.getQuantity());
        orderDetails.setItemName(request.getItemName());
        orderDetails.setCreatedAt(LocalDateTime.now());
        orderDetails.setCustomerId(request.getCustomerId());
        return orderDetails;
    }



    public OrderDetails getOrderEntity(String orderId){
        Optional<OrderDetails> optional = orderDetailsRepository.findByOrderId(orderId);
        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new EntityNotFoundException(
                    String.format("Entity not found for orderId=%s", orderId)
            );
        }
    }

    public String getOrderStatus(String OrderId) {
        Optional<OrderStatus> order = orderStatusRepository.findByOrderId(OrderId);
        return order.map(OrderStatus::toString).orElse("Order not found.");
    }

    public void setOrderStatus(OrderDetails request) {
        OrderStatus orderStatus = buildOrderStatus(request);
        orderStatusRepository.save(orderStatus);
    }

    private static OrderStatus buildOrderStatus(OrderDetails request) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(request.getOrderId());
        orderStatus.setStatus("CREATED");
        orderStatus.setDateTime(request.getCreatedAt());
        return orderStatus;
    }
}
