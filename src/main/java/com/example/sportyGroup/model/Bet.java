package com.example.sportyGroup.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bet")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "session_key")
    private Integer sessionKey;

    @Column(name = "driver_number")
    private Integer driverNumber;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "odds")
    private Integer odds;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "status")
    private String status = "PENDING";

    @Column(name = "prize")
    private Double prize;
}
