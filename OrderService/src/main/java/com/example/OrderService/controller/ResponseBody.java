package com.example.OrderService.controller;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBody {
    int orderId;
    int CustomerID;
    String itemName;
    int Quantity;
}
