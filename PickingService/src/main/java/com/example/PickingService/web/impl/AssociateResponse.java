package com.example.PickingService.web.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AssociateResponse {
    Long associateId;
    String firstName;
    String lastName;
    String assignedOrderId;
    String status;
    String loginTime;
    String logoutTime;
}
