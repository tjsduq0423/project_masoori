package com.fintech.masoori.global.rabbitMQ.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fintech.masoori.global.rabbitMQ.dto.AnalyticsReq;
import com.fintech.masoori.global.rabbitMQ.dto.RabbitTestMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnalyticsPubService {
	private final RabbitTemplate rabbitTemplate;
	@Value("${rabbitmq.queue.analytics}")
	private String routingKey;

	public void sendMessage(AnalyticsReq analyticsReq) {
		log.info("Sent Msg : {}", analyticsReq);
		rabbitTemplate.convertAndSend(routingKey, analyticsReq);
	}
}