package com.fintech.masoori.domain.card.repository;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.cglib.core.Local;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fintech.masoori.domain.card.dto.CardType;
import com.fintech.masoori.domain.card.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
	@Query("SELECT c FROM Card c WHERE c.user.id = :userId AND c.cardType = :type AND c.createdDate >= :startDate AND  c.createdDate <= :endDate ORDER BY c.createdDate DESC")
	List<Card> findRangeCard(@Param("userId") long userId, @Param("type") CardType type,
		@Param("startDate") LocalDateTime startDate,
		@Param("endDate") LocalDateTime endDate);

	@Query("SELECT c FROM Card c WHERE c.user.id = :userId AND c.cardType = :type AND c.id = :cardId")
	Card findCard(@Param("userId") long userId, @Param("type") CardType type, @Param("cardId") long cardId);

	@Query("SELECT c FROM Card c WHERE c.user.id = :userId AND c.cardType = :type AND c.createdDate >= :startDate AND c.createdDate <= :endDate ")
	Card findRecentCard(@Param("userId") long userId, @Param("type") CardType type, @Param("startDate")LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

	@Query("SELECT c FROM Card c WHERE c.id = :cardId AND c.cardType = :type AND c.createdDate >= :startDate AND c.createdDate <= :endDate")
	Card findCardByCardId(@Param("cardId") long cardId, @Param("type") CardType type, @Param("startDate")LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

	Card findCardByUserIdAndId(Long userId, Long id);

	@Query("SELECT c FROM Card c WHERE c.user.id = :userId AND c.cardType = :type AND c.createdDate <= :now ORDER BY c.createdDate DESC")
	Card findTopByUserIdRecentlyChallengeCard(@Param("userId") long userId, @Param("type") CardType type, @Param("now")LocalDateTime now, PageRequest pageable);

	@Query("SELECT c FROM Card c WHERE c.user.id = :userId AND c.cardType = :type AND c.createdDate >= :startDate AND c.createdDate <= :endDate")
	Card findSpecialCardByUserId(@Param("userId") long userId, @Param("type") CardType type, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
