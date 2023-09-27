package com.fintech.masoori.global.rabbitMQ.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fintech.masoori.global.rabbitMQ.dto.RealTimeReq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RealTimePubService {
	private final RabbitTemplate rabbitTemplate;
	@Value("${rabbitmq.queue.realtime}")
	private String routingKey;

	public void sendMessage(RealTimeReq realTimeReq) {
		log.info("Sent Msg : {}", realTimeReq);
		rabbitTemplate.convertAndSend(routingKey, realTimeReq);
	}
}
