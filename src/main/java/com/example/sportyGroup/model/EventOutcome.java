package com.example.sportyGroup.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "event_outcome")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EventOutcome {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "session_key", unique = true)
    private Integer sessionKey;

    @Column(name = "winner_driver_number")
    private Integer winnerDriverNumber;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
