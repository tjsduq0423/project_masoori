package com.fintech.masoori.domain.card.service;

import java.time.LocalDateTime;

import com.fintech.masoori.domain.card.dto.BasicCardRes;
import com.fintech.masoori.domain.card.dto.ChallengeCardRes;

public interface CardService {
	BasicCardRes selectRangeBasicCard(String email, LocalDateTime start, LocalDateTime end);

	BasicCardRes.BasicCard selectBasicCard(String email, long cardId);

	ChallengeCardRes selectRangeChallengeCard(String email, LocalDateTime start, LocalDateTime end);

	ChallengeCardRes.ChallengeCard selectChallengeCard(String email, long cardId);
}
