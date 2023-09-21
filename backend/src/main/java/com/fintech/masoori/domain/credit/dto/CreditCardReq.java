package com.fintech.masoori.domain.credit.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreditCardReq {
	@NotNull
	private final LocalDateTime time;
}
