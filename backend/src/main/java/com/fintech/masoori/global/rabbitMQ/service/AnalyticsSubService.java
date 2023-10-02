package com.fintech.masoori.global.rabbitMQ.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fintech.masoori.domain.analytics.repository.MonthlySpendingAnalyticsRepository;
import com.fintech.masoori.domain.card.service.CardService;
import com.fintech.masoori.global.rabbitMQ.dto.AnalyticsRes;
import com.fintech.masoori.global.rabbitMQ.dto.WeeklyRes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnalyticsSubService {
	MonthlySpendingAnalyticsRepository monthlySpendingAnalyticsRepository;

	@RabbitListener(queues = "${rabbitmq.sub.analytics}")
	public void subscribe(AnalyticsRes analyticsRes) {
		log.info("{}", analyticsRes);
		monthlySpendingAnalyticsRepository.save(analyticsRes.getMonthlySpendingAnalytics());

	}

}
