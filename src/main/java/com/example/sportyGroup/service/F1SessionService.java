package com.example.sportyGroup.service;

import com.example.sportyGroup.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class F1SessionService {

    private static final String SESSIONS_API_URL = "https://api.openf1.org/v1/sessions";
    private static final String DRIVERS_API_URL = "https://api.openf1.org/v1/drivers";
    private final RestTemplate restTemplate = new RestTemplate();
    private final Random random = new Random();

    public List<SessionWithDriversDto> getSessions(String sessionType, Integer year, String country) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(SESSIONS_API_URL);
        
        if (sessionType != null) builder.queryParam("session_name", sessionType);
        if (year != null) builder.queryParam("year", year);
        if (country != null) builder.queryParam("country_name", country);

        F1SessionDto[] sessions = restTemplate.getForObject(builder.toUriString(), F1SessionDto[].class);
        
        if (sessions == null) return List.of();
        
        return Arrays.stream(sessions)
                .map(this::enrichWithDriverMarket)
                .collect(Collectors.toList());
    }

    private SessionWithDriversDto enrichWithDriverMarket(F1SessionDto session) {
        SessionWithDriversDto result = new SessionWithDriversDto();
        result.setSession(session);
        result.setDriverMarket(getDriverMarket(session.getSessionKey()));
        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return result;
    }

    private List<DriverMarketDto> getDriverMarket(Integer sessionKey) {
        String url = UriComponentsBuilder.fromHttpUrl(DRIVERS_API_URL)
                .queryParam("session_key", sessionKey)
                .toUriString();
        
        DriverDto[] drivers = restTemplate.getForObject(url, DriverDto[].class);
        
        if (drivers == null) return List.of();
        
        return Arrays.stream(drivers)
                .map(driver -> new DriverMarketDto(
                        driver.getFullName(),
                        driver.getDriverNumber(),
                        2 + random.nextInt(3)
                ))
                .collect(Collectors.toList());
    }

    public Integer getDriverOdds(Integer sessionKey, Integer driverNumber) {
        return 2 + random.nextInt(3);
    }
}
