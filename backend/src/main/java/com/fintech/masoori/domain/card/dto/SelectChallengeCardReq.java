package com.fintech.masoori.domain.card.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SelectChallengeCardReq {
	@Schema(description = "조회 시작일", example = "202307")
	private LocalDateTime startTime;
	@Schema(description = "조회 종료일", example = "202309")
	private LocalDateTime endTime;
}
