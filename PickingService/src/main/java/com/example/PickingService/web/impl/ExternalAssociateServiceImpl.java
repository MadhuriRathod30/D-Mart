package com.example.PickingService.web.impl;

import com.example.PickingService.exception.AssociateNotFoundException;
import com.example.PickingService.exception.ExternalServiceException;
import com.example.PickingService.exception.ExternalServiceUnavailableException;
import com.example.PickingService.exception.OrderNotFoundException;
import com.example.PickingService.web.ExternalAssociateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalAssociateServiceImpl implements ExternalAssociateService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Long assignAssociate(String orderId) {
        String assignAssociateURL = "http://localhost:8082/associate/assign/{id}";

        ResponseEntity<AssociateResponse> response;
        try {
            response = restTemplate.exchange(
                    assignAssociateURL,
                    HttpMethod.PUT,
                    new HttpEntity<>(null),
                    AssociateResponse.class,
                    orderId);

            if (response.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)) {
                throw new AssociateNotFoundException(
                        String.format("Associate not found in external service, for picking orderId=%s", orderId)
                );
            } else if (response.getStatusCode().is5xxServerError() || response.getStatusCode().is4xxClientError()) {
                throw new ExternalServiceException(
                        String.format("Failed to assign associate from associateService, Got Status Code =%s ", response.getStatusCode())
                );
            } else {

            return response.getBody().getAssociateId();
            }
        } catch (RestClientException rce) {
            throw new ExternalServiceUnavailableException("External Associate Service is unavailable", rce);
        } catch (IllegalArgumentException ex) {
            throw new ExternalServiceException("Failed to unmarshall response from orderService for orderId=" + orderId);
        }catch (NullPointerException e){
            throw new AssociateNotFoundException("Associate is not found ");
        }


    }

    @Override
    public void unAssignAssociate(Long associateId) {

        String unassignAssociateURL = "http://localhost:8082/associate/unassign/{id}";

        ResponseEntity<Object> response ;
        try {
            response = restTemplate.exchange(
                    unassignAssociateURL,
                    HttpMethod.PUT,
                    new HttpEntity<>(null),
                    Object.class,
                    associateId);
        } catch (RestClientException rce) {
            throw new ExternalServiceUnavailableException("External Associate Service is unavailable", rce);
        }
    }
}
