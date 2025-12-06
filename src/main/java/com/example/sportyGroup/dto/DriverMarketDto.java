package com.example.sportyGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DriverMarketDto {
    private String fullName;
    private Integer driverNumber;
    private Integer odds;
}
