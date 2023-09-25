package com.fintech.masoori.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SendEmailReq {

    @NotBlank
    @Schema(description = "사용자 이메일", example = "ssafy@gmail.com")
    private String email;
}
