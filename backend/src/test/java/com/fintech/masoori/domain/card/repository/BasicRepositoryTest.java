package com.fintech.masoori.domain.card.repository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.card.dto.CardType;
import com.fintech.masoori.domain.card.entity.Basic;
import com.fintech.masoori.domain.card.entity.Card;

@SpringBootTest
@Transactional
class BasicRepositoryTest {
	@Autowired
	CardRepository cardRepository;
	@Autowired
	BasicRepository basicRepository;

	private static final Logger logger = LoggerFactory.getLogger(CardRepositoryTest.class);

	@Test
	void joinBasicCard() {
		Basic basic1 = Basic.builder()
							.keyword("키워드1 리스트")
							.totalAmount(10000)
							.frequency(5)
							.build();
		basicRepository.save(basic1);
		Basic basic2 = Basic.builder()
							.keyword("키워드2 리스트")
							.totalAmount(20000)
							.frequency(6)
							.build();
		basicRepository.save(basic2);
		Basic basic3 = Basic.builder()
							.keyword("키워드3 리스트")
							.totalAmount(30000)
							.frequency(7)
							.build();
		basicRepository.save(basic3);
		Card card = Card.builder()
						.name("소비 카드 테스트")
						.imagePath("D://img/test.img")
						.description("이 카드는 테스트1용 카드")
						.cardType(CardType.BASIC)
						.build();
		card.getBasics().add(basic1);
		card.getBasics().add(basic2);
		card.getBasics().add(basic3);
		cardRepository.save(card);
		Card findCard = cardRepository.findById(card.getId()).orElse(null);
		logger.info("BasicCard : {}", findCard.getBasics().get(0).getClass());
		for (Basic basic : findCard.getBasics()) {
			logger.info("Card : {}, {}", findCard, basic);
			//            logger.info("Basic : {}", basic);
		}
	}
}
