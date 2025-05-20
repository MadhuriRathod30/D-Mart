package com.example.OrderService.model;


import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("status")
public class OrderStatus {

    private String orderId;
    private String status;
    private LocalDateTime dateTime;

}
