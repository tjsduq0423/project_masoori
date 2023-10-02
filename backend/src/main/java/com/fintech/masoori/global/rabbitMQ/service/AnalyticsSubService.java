package com.fintech.masoori.global.rabbitMQ.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fintech.masoori.domain.analytics.service.MonthlySpendingAnalyticsService;
import com.fintech.masoori.domain.credit.service.CreditCardService;
import com.fintech.masoori.domain.user.service.UserService;
import com.fintech.masoori.global.rabbitMQ.dto.MonthlySpendingAndCreditcard;
import com.fintech.masoori.global.sse.service.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnalyticsSubService {
	private UserService userService;
	private MonthlySpendingAnalyticsService monthlySpendingAnalyticsService;
	private CreditCardService creditCardService;
	private NotificationService notificationService;

	@RabbitListener(queues = "analytics.res")
	public void subscribe(MonthlySpendingAndCreditcard monthlySpendingAndCreditcard) {
		log.info("{}", monthlySpendingAndCreditcard);
		monthlySpendingAnalyticsService.saveMonthlySpendingAnalytics(monthlySpendingAndCreditcard);
		creditCardService.saveRecommendedCreditCard(monthlySpendingAndCreditcard);

		String email = userService.findById(monthlySpendingAndCreditcard.getUserId()).getEmail();
		notificationService.notify(email, "Monthly analytics and card recommendations completed");

	}

}
