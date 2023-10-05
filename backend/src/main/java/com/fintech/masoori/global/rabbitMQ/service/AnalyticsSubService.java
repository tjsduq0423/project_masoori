package com.fintech.masoori.global.rabbitMQ.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import net.nurigo.java_sdk.exceptions.CoolsmsException;

import com.fintech.masoori.domain.analytics.service.MonthlySpendingAnalyticsService;
import com.fintech.masoori.domain.credit.service.CreditCardService;
import com.fintech.masoori.domain.user.service.SmsService;
import com.fintech.masoori.domain.user.service.UserService;
import com.fintech.masoori.global.rabbitMQ.dto.MonthlySpendingAndCreditcard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnalyticsSubService {
	private final UserService userService;
	private final MonthlySpendingAnalyticsService monthlySpendingAnalyticsService;
	private final CreditCardService creditCardService;
	private final SmsService smsService;

	@RabbitListener(queues = "analytics.res")
	public void subscribe(MonthlySpendingAndCreditcard monthlySpendingAndCreditcard) throws CoolsmsException {
		log.info("{}", monthlySpendingAndCreditcard);
		// monthlySpendingAnalyticsService.saveMonthlySpendingAnalytics(monthlySpendingAndCreditcard);
		creditCardService.saveRecommendedCreditCard(monthlySpendingAndCreditcard);

		// 문자 보내자
		String phoneNumber = userService.findById(monthlySpendingAndCreditcard.getUserId()).getPhoneNumber();
		smsService.sendCreditCardAlarm(phoneNumber);
	}

}
