package com.example.OrderService.controller;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsRequestBody {
    private int customerID;
    private String itemName;
    private int quantity;
}
