package com.fintech.masoori.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SmsCheckReq {

    @NotBlank
    @Schema(description = "사용자 휴대폰번호", example = "01012345678")
    private String phoneNumber;

    @NotBlank
    @Schema(description = "6자리 숫자 인증코드", example = "997532")
    private String code;
}
