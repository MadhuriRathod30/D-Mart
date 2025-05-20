package com.example.AssociateService.service;

import com.example.AssociateService.dao.AssociateDao;
import com.example.AssociateService.dao.CustomTableCreatorDao;
import com.example.AssociateService.exception.AssociateNotFoundException;
import com.example.AssociateService.model.Associate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class AssociateService {

    @Autowired
    AssociateDao associateDao;

    @Autowired
    CustomTableCreatorDao customTableCreatorDao;

    @Transactional
    public void createTable(){
      customTableCreatorDao.createTableSequence();
    }

    @Transactional
    // how to save via entity manager in hibernate
    public void loginAssociate(Associate associate){
        associateDao.save(associate.getAssociateId() , associate.getFirstName() , associate.getLastName());
    }

    public List<Associate> getAssociate(){
       List<Associate> ls = new ArrayList<>();
       ls = associateDao.findAll();
       return ls;
    }

    @Transactional
    public void logoutAssociate(String orderId){
        associateDao.deleteById(orderId);
    }

    public List<Associate> getAvailableAssociate(){
        List<Associate> ans = new ArrayList<>();
        ans = associateDao.findByStatus("AVAILABLE");
        return ans;
    }


    @Transactional
    public Associate assignAssociate(String orderId ){
        List<Associate> ls = getAvailableAssociate();
        Long associateId = -1L;
        if(!ls.isEmpty()){
            associateId = ls.get(0).getAssociateId();
            associateDao.updateAssign(associateId,orderId);
            return associateDao.findById(associateId);
        }else{
            throw new AssociateNotFoundException(
                    "Associate is not Available for the picking service"
            );
        }

    }

    @Transactional
    public void unassignAssociate(String orderId){
        associateDao.updateUnassign(orderId);
        logoutAssociate(orderId);
    }
}
