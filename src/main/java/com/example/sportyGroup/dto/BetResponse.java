package com.example.sportyGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class BetResponse {
    private UUID betId;
    private Double newBalance;
    private String message;
}
