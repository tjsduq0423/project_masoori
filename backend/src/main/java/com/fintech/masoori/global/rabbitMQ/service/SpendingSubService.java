package com.fintech.masoori.global.rabbitMQ.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import net.nurigo.java_sdk.exceptions.CoolsmsException;

import com.fintech.masoori.domain.card.service.CardService;
import com.fintech.masoori.domain.user.exception.UserNotFoundException;
import com.fintech.masoori.domain.user.repository.UserRepository;
import com.fintech.masoori.domain.user.service.SmsService;
import com.fintech.masoori.global.rabbitMQ.dto.GeneratedSpendingCard;
import com.fintech.masoori.global.sse.service.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpendingSubService {
	private final CardService cardService;
	private final UserRepository userRepository;
	private final SmsService smsService;

	@RabbitListener(queues = "spending.res")
	public void subscribeSpendingQueue(GeneratedSpendingCard generatedSpendingCard) throws CoolsmsException {
		log.info("생성되어 넘겨받은 소비 카드 등록");
		cardService.registerSpendingCard(generatedSpendingCard);
		String phonNumber = userRepository.findById(generatedSpendingCard.getUserId())
		                             .orElseThrow(() -> new UserNotFoundException("User is Not Found"))
		                             .getPhoneNumber();
		//여기.
		smsService.sendGenerationTarotCardAlarm(phonNumber);
	}
}
