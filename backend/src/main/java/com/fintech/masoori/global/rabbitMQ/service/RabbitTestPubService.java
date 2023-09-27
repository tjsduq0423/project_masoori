package com.fintech.masoori.global.rabbitMQ.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fintech.masoori.global.rabbitMQ.dto.RabbitTestMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitTestPubService {
	private final RabbitTemplate rabbitTemplate;
	@Value("${rabbitmq.queue.realtime}")
	private String routingKey;

	public void sendMessage(RabbitTestMessage message) {
		log.info("Sent Msg : {}", message);
		rabbitTemplate.convertAndSend(routingKey, message);
	}
}
