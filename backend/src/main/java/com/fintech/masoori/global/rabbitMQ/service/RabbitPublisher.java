package com.fintech.masoori.global.rabbitMQ.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fintech.masoori.global.rabbitMQ.dto.RabbitMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitPublisher {

	private final RabbitTemplate rabbitTemplate;
	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	@Value("${rabbitmq.routing_key.name1}")
	private String routingKey;

	public void sendMessage(RabbitMessage message) {
		log.info("Sent Msg : {}", message);
		rabbitTemplate.convertAndSend(exchange, routingKey, message);
	}
}
