package com.fintech.masoori.global.rabbitMQ.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fintech.masoori.global.rabbitMQ.dto.ChallengeRequestMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChallengePubService {
	private final RabbitTemplate rabbitTemplate;
	@Value("${rabbitmq.queue.challenge}")
	private String routingKey;

	public void sendMessage(ChallengeRequestMessage message) {
		log.info("Sent Msg : {}", message);
		rabbitTemplate.convertAndSend(routingKey, message);
	}
}
