package com.example.AssociateService.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/associate")
public class AssociateController {

    @Autowired
    private AssociateRepository associateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Associate associate) {
        associate.setPassword(passwordEncoder.encode(associate.getPassword()));
        associateRepository.save(associate);
        return ResponseEntity.ok("Associate registered");
    }

    // Add login/logout APIs if needed
}
