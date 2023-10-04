package com.fintech.masoori.global.rabbitMQ.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fintech.masoori.domain.card.exception.CardNotFound;
import com.fintech.masoori.domain.card.repository.CardRepository;
import com.fintech.masoori.domain.card.service.CardService;
import com.fintech.masoori.global.rabbitMQ.dto.GeneratedChallengeCard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChallengeSubService {
	private final CardService cardService;
	private final CardRepository cardRepository;

	@RabbitListener(queues = "challenge.res")
	public void subscribeChallengeQueue(GeneratedChallengeCard generatedChallengeCard) {
		log.info("생성되어 넘겨받은 챌린지 카드 등록");
		cardService.registerChallengeCard(generatedChallengeCard);

		String email = cardRepository.findById(generatedChallengeCard.getCardId())
		                             .orElseThrow(() -> new CardNotFound("Card is Not Found"))
		                             .getUser()
		                             .getEmail();
		// 여기
	}
}
