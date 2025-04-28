package com.example.OrderService.service;

import com.example.OrderService.controller.ResponseBody;
import com.example.OrderService.model.OrderDetails;
import com.example.OrderService.repository.OrderDetailsRepository;
import org.apache.kafka.common.network.Send;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderDetailsService {

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    public ResponseBody createOrder(OrderDetails request){

        OrderDetails orderDetails = new OrderDetails();

        orderDetails.setQuantity(request.getQuantity());
        orderDetails.setItemName(request.getItemName());
        orderDetails.setCreatedAt(LocalDateTime.now());
        orderDetails.setCustomerId(request.getCustomerId());

        OrderDetails saveOrder= orderDetailsRepository.save(orderDetails);

        ResponseBody responseBody = new ResponseBody();
        responseBody = SendResponseBody(saveOrder);

        return responseBody;

    }

    public ResponseBody SendResponseBody(OrderDetails saveOrder){

        ResponseBody responseBody = new ResponseBody();
        responseBody.setCustomerID(saveOrder.getCustomerId());
        responseBody.setQuantity(saveOrder.getQuantity());
        responseBody.setItemName(saveOrder.getItemName());

        return responseBody;
    }
}
