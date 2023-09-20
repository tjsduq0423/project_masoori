package com.fintech.masoori.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailCheckReq {

    @NotBlank
    @Schema(description = "사용자 이메일", example = "ssafy@gmail.com")
    private String email;

    @NotBlank
    @Schema(description = "8자리 영대문자+숫자 인증코드", example = "D9S3G97T")
    private String code;
}
