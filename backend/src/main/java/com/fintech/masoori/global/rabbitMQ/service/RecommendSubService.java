package com.fintech.masoori.global.rabbitMQ.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fintech.masoori.domain.credit.repository.CreditCardUserRepository;
import com.fintech.masoori.global.rabbitMQ.dto.RecommendRes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecommendSubService {

	private final CreditCardUserRepository creditCardUserRepository;

	@RabbitListener(queues = "${rabbitmq.sub.realtime}")
	public void subscribe(RecommendRes recommendRes) {
		log.info("{}", recommendRes);
		creditCardUserRepository.saveAll(recommendRes.getCreditCardUserList());
	}
}
