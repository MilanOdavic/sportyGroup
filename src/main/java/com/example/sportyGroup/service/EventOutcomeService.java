package com.example.sportyGroup.service;

import com.example.sportyGroup.dto.EventOutcomeRequest;
import com.example.sportyGroup.dto.EventOutcomeResponse;
import com.example.sportyGroup.model.Bet;
import com.example.sportyGroup.model.EventOutcome;
import com.example.sportyGroup.model.Person;
import com.example.sportyGroup.repository.BetRepository;
import com.example.sportyGroup.repository.EventOutcomeRepository;
import com.example.sportyGroup.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventOutcomeService {

    private final EventOutcomeRepository eventOutcomeRepository;
    private final BetRepository betRepository;
    private final PersonRepository personRepository;

    @Transactional
    public EventOutcomeResponse processEventOutcome(EventOutcomeRequest request) {
        if (eventOutcomeRepository.existsBySessionKey(request.getSessionKey())) {
            throw new RuntimeException("Event outcome already processed");
        }

        EventOutcome outcome = EventOutcome.builder()
                .sessionKey(request.getSessionKey())
                .winnerDriverNumber(request.getWinnerDriverNumber())
                .createdAt(LocalDateTime.now())
                .build();
        eventOutcomeRepository.save(outcome);

        List<Bet> bets = betRepository.findAll().stream()
                .filter(bet -> bet.getSessionKey().equals(request.getSessionKey()))
                .filter(bet -> "PENDING".equals(bet.getStatus()))
                .toList();

        int wonBets = 0;
        int lostBets = 0;

        for (Bet bet : bets) {
            if (bet.getDriverNumber().equals(request.getWinnerDriverNumber())) {
                bet.setStatus("WON");
                double prize = bet.getAmount() * bet.getOdds();
                bet.setPrize(prize);
                
                Person user = personRepository.findById(bet.getUserId()).orElseThrow();
                user.setBalance(user.getBalance() + prize);
                personRepository.save(user);
                
                wonBets++;
            } else {
                bet.setStatus("LOST");
                bet.setPrize(0.0);
                lostBets++;
            }
            betRepository.save(bet);
        }

        return new EventOutcomeResponse(
                "Event outcome processed successfully",
                bets.size(),
                wonBets,
                lostBets
        );
    }
}
