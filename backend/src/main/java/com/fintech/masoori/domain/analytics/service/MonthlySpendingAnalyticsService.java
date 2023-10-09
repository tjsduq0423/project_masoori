package com.fintech.masoori.domain.analytics.service;

import java.time.LocalDateTime;
import java.util.List;

import com.fintech.masoori.domain.analytics.dto.MonthlySpendingAnalyticsRes;
import com.fintech.masoori.domain.analytics.entity.MonthlySpendingAnalytics;
import com.fintech.masoori.global.rabbitMQ.dto.MonthlySpendingAndCreditcard;

public interface MonthlySpendingAnalyticsService {
	/**
	 * 월간 분석 전체 조회 by 유저 이메일 &
	 */
	MonthlySpendingAnalyticsRes selectAll(String userEmail, LocalDateTime time);

	void saveMonthlySpendingAnalytics(MonthlySpendingAndCreditcard monthlySpendingAndCreditcard);

	void updateCreatedDateAnalytics(MonthlySpendingAndCreditcard monthlySpendingAndCreditcard);

}
