package com.fintech.masoori.domain.deal.service;

public interface DealService {
	// 월간 비용 조회
	Integer getMonthlySpending(String email);

	// 주간 비용 조회
	Integer getWeeklySpending(String email);

	// 일간 비용 조회
	Integer getDailySpending(String email);
}
