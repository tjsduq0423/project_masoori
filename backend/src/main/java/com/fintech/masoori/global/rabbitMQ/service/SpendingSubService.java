package com.fintech.masoori.global.rabbitMQ.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fintech.masoori.domain.card.exception.CardNotFound;
import com.fintech.masoori.domain.card.repository.CardRepository;
import com.fintech.masoori.domain.card.service.CardService;
import com.fintech.masoori.global.rabbitMQ.dto.GeneratedSpendingCard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpendingSubService {
	private final CardService cardService;
	private final CardRepository cardRepository;

	@RabbitListener(queues = "spending.res")
	public void subscribeSpendingQueue(GeneratedSpendingCard generatedSpendingCard) {
		log.info("생성되어 넘겨받은 소비 카드 등록");
		cardService.registerSpendingCard(generatedSpendingCard);

		String email = cardRepository.findById(generatedSpendingCard.getCardId())
		                             .orElseThrow(() -> new CardNotFound("Card is Not Found"))
		                             .getUser()
		                             .getEmail();
		//여기.
	}
}
