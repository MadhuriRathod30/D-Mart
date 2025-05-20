package com.example.PickingService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExternalServiceExceptionHandler {

    @ExceptionHandler(value = OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException orderNotFoundException){
        ExternalServiceException orderException = new ExternalServiceException(
                orderNotFoundException.getMessage(),
                orderNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(orderException , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AssociateNotFoundException.class)
    public ResponseEntity<Object> handleAssociateNotFoundException(AssociateNotFoundException associateNotFoundException){
        ExternalServiceException associateException = new ExternalServiceException(
                associateNotFoundException.getMessage(),
                associateNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(associateException , HttpStatus.NOT_FOUND);
    }

    public record ExternalServiceException(String message, Throwable throwable, HttpStatus httpStatus) {
    }
}

