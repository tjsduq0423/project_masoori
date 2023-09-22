package com.fintech.masoori.global.rabbitMQ.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fintech.masoori.global.rabbitMQ.dto.RabbitMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RabbitConsumer {
	// @RabbitListener(queues = {"${rabbitmq.queue.name1}"})
	// public void consume(RabbitMessage message) {
	// 	log.info("{}", message);
	// }

	@RabbitListener(queues = "#{queue1.name}")
	public void consume(RabbitMessage message) {
		log.info("{}", message);
	}
}
