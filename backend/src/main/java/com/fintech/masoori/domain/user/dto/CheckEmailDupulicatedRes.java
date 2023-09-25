package com.fintech.masoori.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckEmailDupulicatedRes {
	private Boolean isDuplicated;
}
