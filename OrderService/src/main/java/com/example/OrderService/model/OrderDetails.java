package com.example.OrderService.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("order_list")
public class OrderDetails {

    @Id
    private String orderId;

    private int customerId;
    private String itemName;
    private int quantity;

    private LocalDateTime createdAt;
}
