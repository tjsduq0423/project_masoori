package com.fintech.masoori.domain.analytics.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.analytics.dto.MonthlySpendingAnalyticsRes;
import com.fintech.masoori.domain.analytics.entity.MonthlySpendingAnalytics;
import com.fintech.masoori.domain.analytics.repository.MonthlySpendingAnalyticsRepository;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.repository.UserRepository;
import com.fintech.masoori.global.rabbitMQ.dto.MonthlySpendingAndCreditcard;
import com.fintech.masoori.global.util.CalcDate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MonthlySpendingAnalyticsServiceImpl implements MonthlySpendingAnalyticsService {
	private final UserRepository userRepository;
	private final MonthlySpendingAnalyticsRepository monthlySpendingAnalyticsRepository;

	@Override
	public MonthlySpendingAnalyticsRes selectAll(String userEmail, LocalDateTime time) {
		User user = userRepository.findUserByEmail(userEmail);

		CalcDate.StartEndDate calcDate = CalcDate.calcDate(time, time);

		List<MonthlySpendingAnalytics> monthlySpendingAnalyticsList = monthlySpendingAnalyticsRepository.findMonthlySpendingAnalyticsByUserId(
			user.getId(), calcDate.getStartDate(), calcDate.getEndDate());
		MonthlySpendingAnalyticsRes monthlySpendingAnalyticsRes = MonthlySpendingAnalyticsRes.builder().build();

		List<MonthlySpendingAnalyticsRes.MonthlySpendingAnalytics> monthlySpendingAnalyticsResList = monthlySpendingAnalyticsList.stream()
																																 .map(
																																	 MonthlySpendingAnalyticsRes.MonthlySpendingAnalytics::new)
																																 .collect(
																																	 Collectors.toList());

		return MonthlySpendingAnalyticsRes.builder()
										  .monthlySpendingAnalyticsList(monthlySpendingAnalyticsResList)
										  .build();
	}

	@Override
	@Transactional
	public void saveMonthlySpendingAnalytics(MonthlySpendingAndCreditcard monthlySpendingAndCreditcard) {
		User serviceUser = userRepository.findById(monthlySpendingAndCreditcard.getUserId())
										 .orElseThrow(() -> new RuntimeException("User Not Found"));

		for (MonthlySpendingAndCreditcard.MonthlySpending spending : monthlySpendingAndCreditcard.getMonthlySpendingList()) {
			MonthlySpendingAnalytics analytics = MonthlySpendingAnalytics.builder()
																		 .category(spending.getCategory())
																		 .cost(spending.getCost())
																		 .build();

			monthlySpendingAnalyticsRepository.save(analytics);
			serviceUser.addMonthlySpendingAnalytics(analytics);
		}
	}

}
