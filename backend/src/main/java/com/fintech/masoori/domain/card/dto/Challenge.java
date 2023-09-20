package com.fintech.masoori.domain.card.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Challenge {
	@Schema(description = "id(PK)", example = "1")
	private Long id;
	@Schema(description = "챌린지 성공 여부", example = "true")
	private Boolean isSuccess;
	@Schema(description = "챌린지 이름", example = "챌린지 이름")
	private String name;
	@Schema(description = "챌린지 달성조건", example = "커피 2번 줄이기")
	private String achievementCondition;
	@Schema(description = "챌린지 시작 시간", example = "20230904")
	private LocalDateTime startTime;
	@Schema(description = "챌린지 종료 시간", example = "20230910")
	private LocalDateTime endTime;

	public Challenge(com.fintech.masoori.domain.card.entity.Challenge challenge) {
		this.id = challenge.getId();
		this.isSuccess = challenge.getIsSuccess();
		this.name = challenge.getName();
		this.achievementCondition = challenge.getAchievementCondition();
		this.startTime = challenge.getStartTime();
		this.endTime = challenge.getEndTime();
	}

}
