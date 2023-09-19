package com.fintech.masoori.domain.analytics.service;

import org.springframework.stereotype.Service;

import com.fintech.masoori.domain.analytics.dto.MonthlySpendingAnalyticsRes;
import com.fintech.masoori.domain.analytics.entity.MonthlySpendingAnalytics;
import com.fintech.masoori.domain.analytics.repository.MonthlySpendingAnalyticsRepository;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MonthlySpendingAnalyticsServiceImpl implements MonthlySpendingAnalyticsService {
	private final UserRepository userRepository;
	private final MonthlySpendingAnalyticsRepository monthlySpendingAnalyticsRepository;

	@Override
	public MonthlySpendingAnalyticsRes selectAll(String userEmail) {
		User user = userRepository.findUserByEmail(userEmail);
		return new MonthlySpendingAnalyticsRes(user.getMonthlySpendingAnalyticsList()
		                                           .stream()
		                                           .map(MonthlySpendingAnalyticsRes.MonthlySpendingAnalytics::new)
		                                           .toList());
	}

	@Override
	public void save(MonthlySpendingAnalytics monthlySpendingAnalytics) {
		monthlySpendingAnalyticsRepository.save(monthlySpendingAnalytics);
	}
}
