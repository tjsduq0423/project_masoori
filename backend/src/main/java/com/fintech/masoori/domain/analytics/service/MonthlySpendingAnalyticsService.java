package com.fintech.masoori.domain.analytics.service;

import com.fintech.masoori.domain.analytics.dto.MonthlySpendingAnalyticsRes;
import com.fintech.masoori.domain.analytics.entity.MonthlySpendingAnalytics;

public interface MonthlySpendingAnalyticsService {
	/**
	 * 월간 분석 전체 조회 by 유저 이메일 &
	 */
	MonthlySpendingAnalyticsRes selectAll(String userEmail);

	void save(MonthlySpendingAnalytics monthlySpendingAnalytics);
}
