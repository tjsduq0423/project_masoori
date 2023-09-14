package com.fintech.masoori.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InfoRes {
	private String imagePath;

	private Boolean isPaymentInfoLinked;

	private Boolean kakaoAlarm;

	private int dailySpending;

	private int weeklySpending;

	private int monthlySpending;

}
