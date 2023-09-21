package com.fintech.masoori.domain.card.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SelectBasicCardReq {
	@Schema(description = "조회 시작일(Date 객체)", example = "2023-09-14T15:30:45")
	private LocalDateTime time;
	@Schema(description = "조회 개수", example = "3")
	private int count;
}
