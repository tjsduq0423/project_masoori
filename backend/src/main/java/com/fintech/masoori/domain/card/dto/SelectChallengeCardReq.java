package com.fintech.masoori.domain.card.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SelectChallengeCardReq {
	@Schema(description = "조회 시작일(Date)", example = "2023-07-14T15:30:45")
	private LocalDateTime startTime;
	@Schema(description = "조회 종료일(Date)", example = "2023-09-14T15:30:45")
	private LocalDateTime endTime;
}
