package com.example.AssociateService.dao;

import com.example.AssociateService.AssociateServiceApplication;
import com.example.AssociateService.model.Associate;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface AssociateDao {
    List<Associate> findByStatus(String status);

//    Associate findOneByStatus(String status);
    List<Associate> findAll();

    Associate findById(Long associateId);

    void deleteById(String orderId);

    void updateAssign(Long associate_id , String order_id);

    void updateUnassign(String orderId);
    public void save(Long associate_ID , String firstName , String lastName);

    public void updateStatus(String assignedOrderID , Long associate_ID);
}