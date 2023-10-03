package com.fintech.masoori.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MonthlySpendingGoalReq {
	@NotNull
	@Schema(description = "한 달 소비 목표 금액", example = "1000000")
	private Integer monthlySpending;
}
