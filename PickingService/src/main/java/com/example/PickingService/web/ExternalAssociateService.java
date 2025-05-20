package com.example.PickingService.web;

import org.springframework.http.ResponseEntity;

public interface ExternalAssociateService {

    public Long assignAssociate(String orderId);
    public void unAssignAssociate(Long associateId);

}
