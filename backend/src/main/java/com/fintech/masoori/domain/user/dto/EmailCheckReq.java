package com.fintech.masoori.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailCheckReq {

    @NotBlank
    private String email;

    @NotBlank
    private String code;
}
