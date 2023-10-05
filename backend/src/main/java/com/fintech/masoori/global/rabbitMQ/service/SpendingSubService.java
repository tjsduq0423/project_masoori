package com.fintech.masoori.global.rabbitMQ.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import net.nurigo.java_sdk.exceptions.CoolsmsException;

import com.fintech.masoori.domain.card.exception.CardNotFound;
import com.fintech.masoori.domain.card.repository.CardRepository;
import com.fintech.masoori.domain.card.service.CardService;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.service.SmsService;
import com.fintech.masoori.global.rabbitMQ.dto.GeneratedSpendingCard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpendingSubService {
	private final CardService cardService;
	private final CardRepository cardRepository;
	private final SmsService smsService;

	@RabbitListener(queues = "spending.res")
	public void subscribeSpendingQueue(GeneratedSpendingCard generatedSpendingCard) throws CoolsmsException {
		log.info("생성되어 넘겨받은 소비 카드 등록");
		cardService.registerSpendingCard(generatedSpendingCard);

		User user = cardRepository.findById(generatedSpendingCard.getCardId())
		                          .orElseThrow(() -> new CardNotFound("Card is Not Found"))
		                          .getUser();
		String phoneNumber = user.getPhoneNumber();
		smsService.sendGenerationTarotCardAlarm(phoneNumber);
	}
}
