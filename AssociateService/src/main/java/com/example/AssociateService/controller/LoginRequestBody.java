package com.example.AssociateService.controller;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginRequestBody {

    String firstName;
    String lastName;
}
