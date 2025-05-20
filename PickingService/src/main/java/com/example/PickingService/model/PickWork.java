package com.example.PickingService.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PickWork {

    String OrderID;
    Long AssociateID;
    LocalDateTime pickStartTime;
    LocalDateTime pickEndTime;
    String PickWorkID;
    int orderQuantity;

}
