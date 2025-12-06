package com.example.sportyGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventOutcomeResponse {
    private String message;
    private Integer totalBets;
    private Integer wonBets;
    private Integer lostBets;
}
