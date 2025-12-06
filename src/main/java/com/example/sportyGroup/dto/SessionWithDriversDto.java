package com.example.sportyGroup.dto;

import lombok.Data;
import java.util.List;

@Data
public class SessionWithDriversDto {
    private F1SessionDto session;
    private List<DriverMarketDto> driverMarket;
}
