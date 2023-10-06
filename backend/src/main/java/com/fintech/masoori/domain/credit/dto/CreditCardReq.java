package com.fintech.masoori.domain.credit.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreditCardReq {

	@NotNull
	@Schema(description = "조회 시작일(Date)", example = "2023-09-19T21:11:45")
	private final LocalDateTime time;
}
