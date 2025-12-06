package com.example.sportyGroup.service;

import com.example.sportyGroup.dto.BetResponse;
import com.example.sportyGroup.dto.PlaceBetRequest;
import com.example.sportyGroup.model.Bet;
import com.example.sportyGroup.model.Person;
import com.example.sportyGroup.repository.BetRepository;
import com.example.sportyGroup.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BetService {

    private final BetRepository betRepository;
    private final PersonRepository personRepository;
    private final F1SessionService f1SessionService;

    @Transactional
    public BetResponse placeBet(PlaceBetRequest request) {
        Person user = personRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getBalance() < request.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }

        Integer odds = f1SessionService.getDriverOdds(request.getSessionKey(), request.getDriverNumber());

        Bet bet = Bet.builder()
                .userId(request.getUserId())
                .sessionKey(request.getSessionKey())
                .driverNumber(request.getDriverNumber())
                .amount(request.getAmount())
                .odds(odds)
                .createdAt(LocalDateTime.now())
                .build();

        betRepository.save(bet);

        user.setBalance(user.getBalance() - request.getAmount());
        personRepository.save(user);

        return new BetResponse(bet.getId(), user.getBalance(), "Bet placed successfully");
    }
}
