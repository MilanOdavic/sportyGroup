package com.example.sportyGroup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DriverDto {
    @JsonProperty("driver_number")
    private Integer driverNumber;
    
    @JsonProperty("full_name")
    private String fullName;
}
