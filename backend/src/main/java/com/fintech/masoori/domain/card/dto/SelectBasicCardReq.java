package com.fintech.masoori.domain.card.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SelectBasicCardReq {
	@Schema(description = "조회 시작일", example = "20230901")
	private LocalDateTime time;
	@Schema(description = "조회 개수", example = "3")
	private int count;
}
