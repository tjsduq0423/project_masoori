package com.fintech.masoori.domain.card.dto;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChallengeCardRes {
	@Builder.Default
	private List<ChallengeCard> challengeCardList = new ArrayList<>();

	@Data
	@AllArgsConstructor
	public static class ChallengeCard {
		@Schema(description = "카드 기본 정보")
		private Card card;
		@Schema(description = "카드에 속한 챌린지들")
		@Builder.Default
		private List<Challenge> challenges = new ArrayList<>();
	}
}
