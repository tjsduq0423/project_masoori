package com.fintech.masoori.domain.card.repository;

import com.fintech.masoori.domain.card.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}
