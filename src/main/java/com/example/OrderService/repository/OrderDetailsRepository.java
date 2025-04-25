package com.example.OrderService.repository;

import com.example.OrderService.model.OrderDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends MongoRepository <OrderDetails , String>{
}
