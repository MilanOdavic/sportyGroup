package com.example.sportyGroup.controller;

import com.example.sportyGroup.dto.SessionWithDriversDto;
import com.example.sportyGroup.service.F1SessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/f1/sessions")
public class F1SessionController {

    private final F1SessionService f1SessionService;

    @GetMapping
    @Operation(description = "Get F1 sessions with optional filters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Sessions not found")
    })
    @ResponseStatus(HttpStatus.OK)
    public List<SessionWithDriversDto> getSessions(
            @Parameter(description = "Session type (e.g., Sprint, Race, Qualifying)")
            @RequestParam(required = false) String sessionType,
            @Parameter(description = "Year")
            @RequestParam(required = false) Integer year,
            @Parameter(description = "Country name")
            @RequestParam(required = false) String country) {
        return f1SessionService.getSessions(sessionType, year, country);
    }
}
