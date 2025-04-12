package com.example.OrderService.repository;

import com.example.OrderService.model.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends MongoRepository<OrderStatus , String> {

}
