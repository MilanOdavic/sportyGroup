package com.example.sportyGroup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class F1SessionDto {
    @JsonProperty("session_key")
    private Integer sessionKey;
    
    @JsonProperty("session_name")
    private String sessionName;
    
    @JsonProperty("session_type")
    private String sessionType;
    
    @JsonProperty("country_name")
    private String countryName;
    
    @JsonProperty("year")
    private Integer year;
    
    @JsonProperty("date_start")
    private String dateStart;
    
    @JsonProperty("date_end")
    private String dateEnd;
}
