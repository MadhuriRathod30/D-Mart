package com.example.OrderService.web.impl;

import com.example.OrderService.web.ExternalPickingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ExternalPickingServiceImpl implements ExternalPickingService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void startPicking(String orderId) {
        String PickingServiceURL = "http://localhost:8082/pick/start?order-id" + orderId;

        ResponseEntity<String> response = restTemplate.postForEntity(PickingServiceURL, null, String.class);

    }
}
