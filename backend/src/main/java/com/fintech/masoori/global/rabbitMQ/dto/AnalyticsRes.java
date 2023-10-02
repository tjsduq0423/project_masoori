package com.fintech.masoori.global.rabbitMQ.dto;

import com.fintech.masoori.domain.analytics.entity.MonthlySpendingAnalytics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AnalyticsRes {
	// 분석된 월간 소비내용을 받는다
	private int id;
	private MonthlySpendingAnalytics monthlySpendingAnalytics;

}
