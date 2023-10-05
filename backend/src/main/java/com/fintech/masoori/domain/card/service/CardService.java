package com.fintech.masoori.domain.card.service;

import java.time.LocalDateTime;

import com.fintech.masoori.domain.card.dto.BasicCardRes;
import com.fintech.masoori.domain.card.dto.ChallengeCardRes;
import com.fintech.masoori.domain.card.dto.UserCardListRes;
import com.fintech.masoori.global.rabbitMQ.dto.GeneratedChallengeCard;
import com.fintech.masoori.global.rabbitMQ.dto.GeneratedSpendingCard;

public interface CardService {
	UserCardListRes selectRangeBasicCard(String email, LocalDateTime start, LocalDateTime end);

	BasicCardRes.BasicCard selectBasicCard(String email, long cardId);

	UserCardListRes selectRangeChallengeCard(String email, LocalDateTime start, LocalDateTime end);

	ChallengeCardRes.ChallengeCard selectChallengeCard(String email, long cardId);

	/**
	 * 기본 소비 카드 등록
	 */
	void registerSpendingCard(GeneratedSpendingCard generatedSpendingCard);

	void createSpendingCard(String email);

	void registerChallengeCardImage(String imgPath, Long cardId);

	void addChallenge(Long cardId, String achievementCondition);

	void createChallengeCard(String email);

	BasicCardRes.BasicCard selectUserRecentBasicCard(String email, LocalDateTime time);

	Long findTopByUserIdRecentlyChallengeCard(String email);

	void updateUserProfileImage(String email, Long id);

}
