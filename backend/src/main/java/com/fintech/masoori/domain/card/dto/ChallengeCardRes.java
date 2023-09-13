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
		@Schema(description = "id(PK)", example = "1")
		private Long id;
		@Schema(description = "카드 이름", example = "카드 이름")
		private String name;
		@Schema(description = "사진 경로", example = "/etc/img")
		private String imagePath;
		@Schema(description = "카드 설명", example = "이 카드는...")
		private String description;
		@Schema(description = "카드에 속한 챌린지들")
		@Builder.Default
		private List<Challenge> challenges = new ArrayList<>();
	}
}
