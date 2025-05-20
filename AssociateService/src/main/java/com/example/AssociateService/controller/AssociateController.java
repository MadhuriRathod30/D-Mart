package com.example.AssociateService.controller;

import com.example.AssociateService.model.Associate;
import com.example.AssociateService.service.AssociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/associate")
public class AssociateController {

    @Autowired
    AssociateService associateService;

    @PostMapping("/login")
    public void loginAssociate(@RequestBody LoginRequestBody loginRequestBody){

        associateService.createTable();

        Associate associate = new Associate();
        associate.setFirstName(loginRequestBody.getFirstName());
        associate.setLastName(loginRequestBody.getLastName());
        associateService.loginAssociate(associate);
    }

    @DeleteMapping("/logout/{id}")
    public void logoutAssociate(@PathVariable("id") String orderId){

        associateService.logoutAssociate(orderId);

    }

    @PutMapping("/assign/{id}")
    public Associate assignAssociate(@PathVariable("id") String orderId){
        return associateService.assignAssociate(orderId);
    }

    @PutMapping("/unassign/{id}")
    public void unassignAssociate(@PathVariable("id") String orderId){
        associateService.unassignAssociate(orderId);
    }

    @GetMapping("/availableassociate")
    public List<Associate> getAvailableAssociate(){
        List<Associate> ls = new ArrayList<>();

        ls = associateService.getAvailableAssociate();
        return ls;
    }

    @GetMapping
    public List<Associate> getAssociate(@RequestParam("id") String id ){
        List<Associate> ls = new ArrayList<>();
        ls = associateService.getAssociate();
        return ls;
    }




}
