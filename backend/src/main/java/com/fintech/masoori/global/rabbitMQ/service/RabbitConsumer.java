package com.fintech.masoori.global.rabbitMQ.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fintech.masoori.global.rabbitMQ.dto.RabbitMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RabbitConsumer {
	// @RabbitListener(queues = {"${rabbitmq.queue.realtime}"})
	// public void consume(RabbitMessage message) {
	// 	log.info("{}", message);
	// }

	@RabbitListener(queues = "${rabbitmq.queue.realtime}")
	public void consume(RabbitMessage message) {
		log.info("{}", message);
	}
}
