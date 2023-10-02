package com.fintech.masoori.global.rabbitMQ.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fintech.masoori.global.rabbitMQ.dto.GeneratedChallengeCard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChallengeSubService {

	@RabbitListener(queues = "challenge.res")
	public void subscribeChallengeQueue(GeneratedChallengeCard generatedChallengeCard) {
		log.info("{}", generatedChallengeCard);

	}
}
