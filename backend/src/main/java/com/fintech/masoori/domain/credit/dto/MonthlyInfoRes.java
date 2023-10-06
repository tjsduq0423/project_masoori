package com.fintech.masoori.domain.credit.dto;

import com.fintech.masoori.domain.analytics.dto.MonthlySpendingAnalyticsRes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MonthlyInfoRes {
	private CreditCardRes creditCardRes;
	private MonthlySpendingAnalyticsRes monthlySpendingAnalyticsRes;
}
