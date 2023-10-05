package com.fintech.masoori.global.rabbitMQ.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import net.nurigo.java_sdk.exceptions.CoolsmsException;

import com.fintech.masoori.domain.card.exception.CardNotFound;
import com.fintech.masoori.domain.card.repository.CardRepository;
import com.fintech.masoori.domain.card.service.CardService;
import com.fintech.masoori.domain.user.service.SmsService;
import com.fintech.masoori.global.rabbitMQ.dto.GeneratedChallengeCard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChallengeSubService {
	private final CardService cardService;
	private final CardRepository cardRepository;
	private final SmsService smsService;

	@RabbitListener(queues = "challenge.res")
	public void subscribeChallengeQueue(GeneratedChallengeCard generatedChallengeCard) throws CoolsmsException {
		log.info("생성되어 넘겨받은 챌린지 이미지 등록");
		cardService.registerChallengeCardImage(generatedChallengeCard.getImagePath(),
			generatedChallengeCard.getCardId());
		String phoneNumber = cardRepository.findById(generatedChallengeCard.getCardId())
		                                   .orElseThrow(() -> new CardNotFound("Card is Not Found"))
		                                   .getUser()
		                                   .getPhoneNumber();
		smsService.sendChallengeAlarm(phoneNumber);
	}
}
