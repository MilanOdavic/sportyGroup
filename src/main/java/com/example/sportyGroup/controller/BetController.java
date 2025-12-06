package com.example.sportyGroup.controller;

import com.example.sportyGroup.dto.BetResponse;
import com.example.sportyGroup.dto.PlaceBetRequest;
import com.example.sportyGroup.service.BetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bets")
public class BetController {

    private final BetService betService;

    @PostMapping("/place")
    @Operation(description = "Place a bet on a driver for an F1 session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bet placed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request or insufficient balance")
    })
    @ResponseStatus(HttpStatus.OK)
    public BetResponse placeBet(@RequestBody PlaceBetRequest request) {
        return betService.placeBet(request);
    }
}
