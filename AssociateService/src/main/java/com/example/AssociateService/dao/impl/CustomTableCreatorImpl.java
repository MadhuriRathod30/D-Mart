package com.example.AssociateService.dao.impl;

import com.example.AssociateService.dao.CustomTableCreatorDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class CustomTableCreatorImpl implements CustomTableCreatorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createTableSequence() {
        String jpql = "CREATE TABLE IF NOT EXISTS associates (" +
                "associate_id SERIAL PRIMARY KEY," +
                "assigned_order_id VARCHAR(100)," +
                "first_name VARCHAR(100) NOT NULL," +
                "last_name VARCHAR(100) NOT NULL," +
                "login_time TIMESTAMP," +
                "logout_time TIMESTAMP," +
                "status VARCHAR(50)" +
                ");";

        entityManager.createNativeQuery(jpql).executeUpdate();
        String query = "CREATE SEQUENCE IF NOT EXISTS associates_seq START WITH 1 INCREMENT BY 1";

        entityManager.createNativeQuery(query).executeUpdate();


    }
}
