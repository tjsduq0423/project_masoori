package com.fintech.masoori.global.rabbitMQ.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fintech.masoori.global.rabbitMQ.dto.AnalyticsRequestMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnalyticsPubService {
	private final RabbitTemplate rabbitTemplate;
	@Value("${rabbitmq.queue.analytics}")
	private String routingKey;

	public void sendMessage(AnalyticsRequestMessage analyticsRequestMessage) {
		log.info("Sent Msg : {}", analyticsRequestMessage);
		rabbitTemplate.convertAndSend(routingKey, analyticsRequestMessage);
	}
}