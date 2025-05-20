package com.example.PackingService.service;

import com.example.PackingService.model.PackingWork;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class PackingService {

    @GetMapping
    public PackingWork startPacking(String orderId){
        return new PackingWork();
    }

}
