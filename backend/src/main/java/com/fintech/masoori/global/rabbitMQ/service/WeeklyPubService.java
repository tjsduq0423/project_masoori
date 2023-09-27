package com.fintech.masoori.global.rabbitMQ.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fintech.masoori.global.rabbitMQ.dto.RecommendReq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeeklyPubService {
	private final RabbitTemplate rabbitTemplate;
	@Value("${rabbitmq.queue.recommend}")
	private String routingKey;

	public void sendMessage(RecommendReq recommendReq) {
		log.info("Sent Msg : {}", recommendReq);
		rabbitTemplate.convertAndSend(routingKey, recommendReq);
	}

}
