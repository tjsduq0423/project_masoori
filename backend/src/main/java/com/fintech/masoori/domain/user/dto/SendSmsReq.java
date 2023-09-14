package com.fintech.masoori.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SendSmsReq {

	@NotBlank
	private String name;

	@NotBlank
	private String phoneNumber;

	// 로그인 기능 구현되면 지울 것
	@NotBlank
	private String email;
}
