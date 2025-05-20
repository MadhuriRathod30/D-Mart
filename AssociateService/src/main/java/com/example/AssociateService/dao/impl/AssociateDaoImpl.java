package com.example.AssociateService.dao.impl;

import com.example.AssociateService.AssociateServiceApplication;
import com.example.AssociateService.dao.AssociateDao;
import com.example.AssociateService.exception.AssociateNotFoundException;
import com.example.AssociateService.model.Associate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TransactionRequiredException;
import jakarta.persistence.TypedQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class AssociateDaoImpl implements AssociateDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Associate> findByStatus(String status) {
        String jpql = "SELECT a FROM Associate a WHERE a.status = :status";
        TypedQuery<Associate> query = entityManager.createQuery(
                jpql, Associate.class);
        query.setParameter("status", "AVAILABLE");
        return query.getResultList();
    }

    @Override
    public List<Associate> findAll() {
        String jpql = "SELECT a FROM Associate a";
        TypedQuery<Associate> query =  entityManager.createQuery(
                jpql , Associate.class);

        return query.getResultList();
    }

    @Override
    public Associate findById(Long associateId) {
        String jpql = "SELECT a from Associate a WHERE a.associateId = :Id ";
        TypedQuery<Associate> query = entityManager.createQuery(jpql , Associate.class);
        query.setParameter("Id", associateId);
        return query.getSingleResult();
    }

    @Override
    public void deleteById(String id) {

        Associate associate = entityManager.find(Associate.class , id);
        entityManager.remove(associate);


    }

    @Override
    public void updateAssign(Long associateId, String orderId) {
       try {
           Associate associate = entityManager.find(Associate.class, associateId);
           associate.setAssignedOrderId(orderId);
           associate.setStatus("ASSIGNED");
           entityManager.merge(associate);
       } catch (IllegalArgumentException | TransactionRequiredException ex){
           throw new AssociateNotFoundException(
                   String.format("Associate is not available for order %s"+orderId)
           );

        }
    }

    @Override
    public void updateUnassign(String orderId) {
        Associate associate = entityManager.find(Associate.class,orderId);
        associate.setStatus("AVAILABLE");
        entityManager.merge(associate);
    }


    @Override
    public void save( Long associateId , String firstName , String lastName ) {

        Associate associate1 = new Associate();
        associate1.setAssociateId(associateId);
        associate1.setFirstName(firstName);
        associate1.setLastName(lastName);
        associate1.setLoginTime(LocalDateTime.now());
        associate1.setStatus("AVAILABLE");

        entityManager.persist(associate1);

    }

    @Override
    public void updateStatus(String orderId, Long associateId) {

    }


}
