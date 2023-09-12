package com.fintech.masoori.domain.card.repository;

import com.fintech.masoori.domain.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CardRepository extends JpaRepository<Card, Long> {
}
