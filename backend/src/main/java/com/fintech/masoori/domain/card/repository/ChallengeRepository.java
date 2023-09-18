package com.fintech.masoori.domain.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintech.masoori.domain.card.entity.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}
