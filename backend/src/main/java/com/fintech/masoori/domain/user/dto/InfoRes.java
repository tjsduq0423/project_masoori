package com.fintech.masoori.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class InfoRes {
	@Schema(description = "사진 경로", example = "/etc/img")
	private String imagePath;

	@Schema(description = "SMS 알림 연동 여부", example = "true")
	private Boolean smsAlarm;

	@Schema(description = "소비카드 생성 연동 여부", example = "true")
	private Boolean cardGeneration;

	@Schema(description = "일별 소비 금액", example = "10000")
	private Integer dailySpending;

	@Schema(description = "주별 소비 금액", example = "100000")
	private Integer weeklySpending;

	@Schema(description = "월별 소비 금액", example = "500000")
	private Integer monthlySpending;

	@Schema(description = "월별 소비 목표 금액", example = "1000000")
	private Integer monthlySpendingGoal;

	@Schema(description = "결제 내역 연동 여부 ", example = "true")
	private Boolean isAuthenticated;

	@Schema(description = "가장 최근에 생성된 챌린지카드 id", example = "1")
	private Long challengeCardId;
}
