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
	 * 챌린지 카드 등록
	 * 카드 이름, 사진 경로, 카드 설명 ,type =  SPECIAL , 챌린지 객체 등록, 성공여부 defalut, 챌린지 이름, 달성 조건 , 시간 defalut
	 */
	void registerChallengeCard(GeneratedChallengeCard challengeCard);

	/**
	 * 기본 소비 카드 등록
	 */
	void registerSpendingCard(GeneratedSpendingCard generatedSpendingCard);

	void createSpendingCard(String email);

	BasicCardRes.BasicCard selectUserRecentBasicCard(String email, LocalDateTime time);

	void updateUserProfileImage(String email, Long id);

}
