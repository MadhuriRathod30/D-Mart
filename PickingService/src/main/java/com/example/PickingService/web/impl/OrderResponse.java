package com.example.PickingService.web.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OrderResponse {

    private String orderId;
    private int customerId;
    private String itemName;
    private int quantity;

}
