package com.example.AssociateService.entity;

@Entity
public class AssociateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role; // e.g., "ROLE_PICKER" or "ROLE_PACKER"

    // Getters, Setters, etc.
}
