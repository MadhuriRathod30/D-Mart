package com.example.PickingService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;

public class AssociateNotFoundException extends RuntimeException{

    public AssociateNotFoundException(String message) {
        super(message);
    }

    public AssociateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
