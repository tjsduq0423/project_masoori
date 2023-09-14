package com.fintech.masoori.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SmsCheckReq {

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String code;
}
