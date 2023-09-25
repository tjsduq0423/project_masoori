package com.fintech.masoori.global.rabbitMQ.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RabbitTestSub {
	@RabbitListener(queues = "${rabbitmq.queue.realtime}")
	public void subTest(String message) {
		log.info("{}", message);
	}
}
