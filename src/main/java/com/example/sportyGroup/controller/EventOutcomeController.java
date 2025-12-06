package com.example.sportyGroup.controller;

import com.example.sportyGroup.dto.EventOutcomeRequest;
import com.example.sportyGroup.dto.EventOutcomeResponse;
import com.example.sportyGroup.service.EventOutcomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventOutcomeController {

    private final EventOutcomeService eventOutcomeService;

    @PostMapping("/outcome")
    @Operation(description = "Process F1 event outcome and settle bets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event outcome processed successfully"),
            @ApiResponse(responseCode = "400", description = "Event already processed")
    })
    @ResponseStatus(HttpStatus.OK)
    public EventOutcomeResponse processOutcome(@RequestBody EventOutcomeRequest request) {
        return eventOutcomeService.processEventOutcome(request);
    }
}
