package com.fintech.masoori.domain.card.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fintech.masoori.domain.card.dto.CardType;
import com.fintech.masoori.domain.card.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
	@Query("SELECT c FROM Card c WHERE c.user.id = :userId AND c.cardType = :type AND c.createdDate >= :startDate AND  c.createdDate <= :endDate")
	List<Card> findRangeCard(@Param("userId") long userId, @Param("type") CardType type,
		@Param("startDate") LocalDateTime startDate,
		@Param("endDate") LocalDateTime endDate);

	@Query("SELECT c FROM Card c WHERE c.user.id = :userId AND c.cardType = :type AND c.id = :cardId")
	Card findCard(@Param("userId") long userId, @Param("type") CardType type, @Param("cardId") long cardId);
}
