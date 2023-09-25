package com.fintech.masoori.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SendSmsReq {

	@NotBlank
	@Schema(description = "사용자 이름", example = "홍길동")
	private String name;

	@NotBlank
	@Schema(description = "사용자 휴대폰번호", example = "01012345678")
	private String phoneNumber;

}
