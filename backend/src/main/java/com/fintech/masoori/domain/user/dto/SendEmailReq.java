package com.fintech.masoori.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SendEmailReq {
    @NotBlank
    private String email;
}
