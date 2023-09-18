package com.fintech.masoori.domain.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintech.masoori.domain.card.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
}
