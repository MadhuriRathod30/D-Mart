package com.example.PickingService.web.impl;

import com.example.PickingService.exception.ExternalServiceException;
import com.example.PickingService.exception.ExternalServiceUnavailableException;
import com.example.PickingService.exception.OrderNotFoundException;
import com.example.PickingService.web.ExternalOrderService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalOrderServiceImpl implements ExternalOrderService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public int getOrderQuantity(String orderId) {
        String OrderServiceURL = "http://localhost:8081/order/" + orderId;
        ResponseEntity<Object> response;
        try {
            response = restTemplate.getForEntity(OrderServiceURL, Object.class);
            if (response.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)) {
                throw new OrderNotFoundException(
                        String.format("Order not found in order service, orderId=%s", orderId)
                );
            } else if (response.getStatusCode().is5xxServerError() || response.getStatusCode().is4xxClientError()) {
                throw new ExternalServiceException(
                        String.format("Failed to get orderDetails from orderService, Got Status Code =%s ", response.getStatusCode())
                );
            } else {

                ObjectMapper mapper = new ObjectMapper();
                OrderResponse orderResponse = mapper.convertValue(response.getBody(), OrderResponse.class);
                return orderResponse.getQuantity();

            }
        } catch (RestClientException rce) {
            throw new ExternalServiceUnavailableException("External Order Service is unavailable", rce);
        }
        catch (IllegalArgumentException ex) {
            throw new ExternalServiceException("Failed to unmarshall response from orderService for orderId="+orderId);
        }

    }
}
