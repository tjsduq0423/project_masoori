package com.fintech.masoori.global.rabbitMQ.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fintech.masoori.global.rabbitMQ.dto.GeneratedSpendingCard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpendingSubService {
	@RabbitListener(queues = "$spending.res")
	public void subTest(GeneratedSpendingCard message) {
		log.info("{}", message);
		//DB 넣기
		//sse 클라이언트 알리기
	}
}
