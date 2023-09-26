package com.fintech.masoori.domain.card.service;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.card.dto.BasicCardRes;
import com.fintech.masoori.domain.card.dto.CardType;
import com.fintech.masoori.domain.card.dto.ChallengeCardRes;
import com.fintech.masoori.domain.card.dto.UserCardListRes;
import com.fintech.masoori.domain.card.entity.Basic;
import com.fintech.masoori.domain.card.entity.Card;
import com.fintech.masoori.domain.card.entity.Challenge;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.global.oauth.ProviderType;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
@Slf4j
class CardServiceImplTest {

	@Autowired
	EntityManager em;
	@Autowired
	CardService cardService;

	@Test
	void 사용자_소비카드_범위_조회() {
		User user = User.builder()
						.email("test@gmail.com")
						.providerType(ProviderType.LOCAL)
						.build();
		em.persist(user);
		List<Basic> basicList = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			basicList.add(Basic.builder().keyword("음식").totalAmount(1000 * i).frequency(2 * i).build());
		}
		List<Card> cardList = new ArrayList<>();
		for (int i = 1; i <= 2; i++) {
			Card card = Card.builder()
							.user(user)
							.name(i + "번 카드")
							.imagePath(i + "번 경로")
							.description(i + "번 카드 설명")
							.cardType(
								CardType.BASIC)
							.basicList(basicList)
							.build();
			em.persist(card);
			cardList.add(card);
		}
		em.flush();
		for (int i = 0; i < cardList.size(); i++) {
			cardList.get(i).setLocalDateTime(LocalDateTime.of(2023, 6 + i, 10 + i, 10, 10, 10));
		}
		em.flush();
		UserCardListRes response = cardService.selectRangeBasicCard(user.getEmail(), LocalDateTime.of(2023, 7, 1, 1, 1),
			LocalDateTime.of(2023, 7, 1, 1, 1));
		assertThat(response.getUserCardList().size() == 1);
		response = cardService.selectRangeBasicCard(user.getEmail(), LocalDateTime.of(2023, 8, 1, 1, 1),
			LocalDateTime.of(2023, 8, 1, 1, 1));
		assertThat(response.getUserCardList().size() == 1);
		response = cardService.selectRangeBasicCard(user.getEmail(), LocalDateTime.of(2023, 9, 1, 1, 1),
			LocalDateTime.of(2023, 9, 1, 1, 1));
		assertThat(response.getUserCardList().size() == 0);
		response = cardService.selectRangeBasicCard(user.getEmail(), LocalDateTime.of(2023, 7, 1, 1, 1),
			LocalDateTime.of(2023, 9, 1, 1, 1));
		assertThat(response.getUserCardList().size() == 2);
		log.info("Response : {}", response);
	}

	/**
	 * 소비 카드 한장 조회
	 */
	@Test
	void 사용자_소비카드_카드ID_조회() {
		User user = User.builder()
						.email("test@gmail.com")
						.providerType(ProviderType.LOCAL)
						.build();
		em.persist(user);
		List<Basic> basicList = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			basicList.add(Basic.builder().keyword("음식").totalAmount(1000 * i).frequency(2 * i).build());
		}
		Card card = Card.builder()
						.user(user)
						.name("1번 카드")
						.imagePath("1번 경로")
						.description("1번 카드 설명")
						.cardType(CardType.BASIC)
						.basicList(basicList)
						.build();
		em.persist(card);
		em.flush();
		BasicCardRes.BasicCard basicCard = cardService.selectBasicCard(user.getEmail(), card.getId());
		assertThat(basicCard.getCard().getId().equals(card.getId()));
		assertThat(basicCard.getCard().getName().equals(card.getName()));
	}

	/**
	 * 챌린지 카드 범위 조회
	 */
	@Test
	void 사용자_챌린지카드_범위_조회() {
		User user = User.builder()
						.email("test@gmail.com")
						.providerType(ProviderType.LOCAL)
						.build();
		em.persist(user);
		List<Challenge> challengeList = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			challengeList.add(Challenge.builder()
									   .isSuccess(false)
									   .name(i + "번 챌린지")
									   .achievementCondition("절약하기")
									   .startTime(LocalDateTime.of(2023, 9, 18, 0, 0))
									   .endTime(LocalDateTime.of(2023, 9, 24, 23, 59))
									   .build());
		}
		List<Card> cardList = new ArrayList<>();
		for (int i = 1; i <= 2; i++) {
			Card card = Card.builder()
							.user(user)
							.name(i + "번 카드")
							.imagePath(i + "번 경로")
							.description(i + "번 카드 설명")
							.cardType(
								CardType.SPECIAL)
							.challengeList(challengeList)
							.build();
			em.persist(card);
			cardList.add(card);
		}
		em.flush();
		for (int i = 0; i < cardList.size(); i++) {
			cardList.get(i).setLocalDateTime(LocalDateTime.of(2023, 8 + i, 1, 0, 0));
		}
		em.flush();
		UserCardListRes challengeCardList = cardService.selectRangeChallengeCard(user.getEmail(),
			LocalDateTime.of(2023, 7, 1, 1, 1), LocalDateTime.of(2023, 7, 1, 1, 1));
		assertThat(challengeCardList.getUserCardList().size() == 0);
		challengeCardList = cardService.selectRangeChallengeCard(user.getEmail(),
			LocalDateTime.of(2023, 8, 1, 1, 1), LocalDateTime.of(2023, 8, 1, 1, 1));
		assertThat(challengeCardList.getUserCardList().size() == 1);
		challengeCardList = cardService.selectRangeChallengeCard(user.getEmail(),
			LocalDateTime.of(2023, 9, 1, 1, 1), LocalDateTime.of(2023, 9, 1, 1, 1));
		assertThat(challengeCardList.getUserCardList().size() == 1);
		challengeCardList = cardService.selectRangeChallengeCard(user.getEmail(),
			LocalDateTime.of(2023, 8, 1, 1, 1), LocalDateTime.of(2023, 9, 1, 1, 1));
		assertThat(challengeCardList.getUserCardList().size() == 2);
	}

	/**
	 * 챌린지 카드 한장 조회
	 */
	@Test
	void 사용자_챌린지카드_카드ID_조회() {
		User user = User.builder()
						.email("test@gmail.com")
						.providerType(ProviderType.LOCAL)
						.build();
		em.persist(user);
		List<Challenge> challengeList = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			challengeList.add(Challenge.builder()
									   .isSuccess(false)
									   .name(i + "번 챌린지")
									   .achievementCondition("절약하기")
									   .startTime(LocalDateTime.of(2023, 9, 18, 0, 0))
									   .endTime(LocalDateTime.of(2023, 9, 24, 23, 59))
									   .build());
		}
		Card card = Card.builder()
						.user(user)
						.name("1번 카드")
						.imagePath("1번 경로")
						.description("1번 카드 설명")
						.cardType(CardType.SPECIAL)
						.challengeList(challengeList)
						.build();
		em.persist(card);
		em.flush();
		ChallengeCardRes.ChallengeCard challengeCard = cardService.selectChallengeCard(user.getEmail(), card.getId());
		assertThat(challengeCard.getCard().getId().equals(card.getId()));
		assertThat(challengeCard.getCard().getName().equals(card.getName()));
	}
}
