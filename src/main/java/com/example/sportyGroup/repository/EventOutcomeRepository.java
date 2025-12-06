package com.example.sportyGroup.repository;

import com.example.sportyGroup.model.EventOutcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventOutcomeRepository extends JpaRepository<EventOutcome, UUID> {
    boolean existsBySessionKey(Integer sessionKey);
}
