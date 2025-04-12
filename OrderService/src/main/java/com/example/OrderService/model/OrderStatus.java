package com.example.OrderService.model;


import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("Order_Status")
public class OrderStatus {

    @Id
    private String ID;

    private String OrderID;
    private String statusOrd;
    private LocalDateTime dateTime;

}
