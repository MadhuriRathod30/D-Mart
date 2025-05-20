package com.example.AssociateService.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "associates")
public class Associate {

    @Id@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "associate_seq_gen")
    @SequenceGenerator(
            name = "associate_seq_gen",
            sequenceName = "associates_seq", // Must match DB sequence name
            allocationSize = 1
    )
    @Column(name="associate_id")
    Long associateId;

    @Column(name ="first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "assigned_order_id")
    String assignedOrderId;

    @Column(name = "status")
    String status; ///Assigned or Available

    @Column(name = "login_time")
    LocalDateTime loginTime;

    @Column(name = "logout_time")
    LocalDateTime logoutTime;

}
