package com.fintech.masoori.domain.card.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.card.dto.CardType;
import com.fintech.masoori.domain.card.entity.Card;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.repository.UserRepository;
import com.fintech.masoori.global.oauth.ProviderType;

@SpringBootTest
@Transactional
class CardRepositoryTest {

	@Autowired
	CardRepository cardRepository;
	@Autowired
	UserRepository userRepository;
	private static final Logger logger = LoggerFactory.getLogger(CardRepositoryTest.class);

	@Test
	void findCardById() {
		Card card1 = Card.builder()
						 .name("카드 테스트1")
						 .imagePath("D://img/test.img")
						 .description("이 카드는 테스트1용 카드")
						 .cardType(CardType.BASIC)
						 .build();
		cardRepository.save(card1);
		User user = User.builder()
						.email("test@gmail.com")
						.nickname("테스트")
						.password("1234")
						.profile("C://test/1.jpg")
						.providerType(ProviderType.LOCAL)
						.build();
		user.getCardList().add(card1);
		//        logger.info("UserCardClass : {}", user.getCards().get(0).getClass());
		userRepository.save(user);
		User findUser = userRepository.findById(user.getId()).orElse(null);
		logger.info("CardClass : {}", findUser.getCardList().get(0).getClass());
		logger.info("CardSize : {}", findUser.getCardList().size());
		for (Card card : findUser.getCardList()) {
			logger.info("Card : {}", card);
		}
		assertThat(findUser.getCardList().get(0).getId()).isEqualTo(card1.getId());
	}
}
