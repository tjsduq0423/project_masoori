package com.fintech.masoori.global.rabbitMQ.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import net.nurigo.java_sdk.exceptions.CoolsmsException;

import com.fintech.masoori.domain.card.exception.CardNotFound;
import com.fintech.masoori.domain.card.repository.CardRepository;
import com.fintech.masoori.domain.card.service.CardService;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.service.SmsService;
import com.fintech.masoori.global.rabbitMQ.dto.GeneratedSpending;
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
		// User user = cardRepository.findById(generatedSpendingCard.getCardId())
		//                           .orElseThrow(() -> new CardNotFound("Card is Not Found"))
		//                           .getUser();
		//spending 으로 소비 내역 생성해서 challenge 추가
		// List<GeneratedSpending> spendings = generatedSpendingCard.getSpendings();
		// Optional<GeneratedSpending> maxSpending = spendings.stream()
		//                                                    .max(
		// 	                                                   Comparator.comparing(GeneratedSpending::getTotalAmount));
		// if (maxSpending.isPresent()) {
		// 	GeneratedSpending maxSpendingObject = maxSpending.get();
		// 	int ap = (int)(maxSpendingObject.getTotalAmount() * 0.15);
		// 	String achievementCondition = maxSpendingObject.getKeyword() + "에서 아껴서 " + ap + "원 저축 하기";
		// 	cardService.addChallenge(user.getId(), achievementCondition);
		// }
		cardService.registerSpendingCard(generatedSpendingCard);

		// String phoneNumber = user.getPhoneNumber();
		// smsService.sendGenerationTarotCardAlarm(phoneNumber);
	}
}
