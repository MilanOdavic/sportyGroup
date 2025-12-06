package com.example.sportyGroup.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PlaceBetRequest {
    private UUID userId;
    private Integer sessionKey;
    private Integer driverNumber;
    private Double amount;
}
