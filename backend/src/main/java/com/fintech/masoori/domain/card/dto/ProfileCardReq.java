package com.fintech.masoori.domain.card.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProfileCardReq {
	@NotBlank
	@Schema(description = "사용자 프로필 이미지로 설정할 이미지의 ID", example = "12")
	private Long id;
}
