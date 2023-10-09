package com.fintech.masoori.domain.card.controller;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fintech.masoori.domain.card.dto.BasicCardRes;
import com.fintech.masoori.domain.card.dto.CardType;
import com.fintech.masoori.domain.card.entity.Basic;
import com.fintech.masoori.domain.card.entity.Card;
import com.fintech.masoori.domain.card.exception.AlreadyInProgressException;
import com.fintech.masoori.domain.card.exception.CanCreateException;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.global.oauth.ProviderType;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
@Slf4j
class CardControllerTest {
	@Autowired
	EntityManager em;
	@Autowired
	CardController cardController;

	private User testUser;

	private Card card;

	@BeforeEach
	void setUp() {
		testUser = User.builder()
					   .email("test123232@gmail.com")
					   .password("123")
					   .providerType(ProviderType.LOCAL)
					   .build();
		em.persist(testUser);
		em.flush();
	}

	@Test()
	void 사용자_최근_생성_카드_조회() {
		List<Basic> basicList = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			basicList.add(Basic.builder().keyword("음식").totalAmount(1000 * i).frequency(2 * i).build());
		}
		card = Card.builder()
				   .user(testUser)
				   .name("1번 카드")
				   .imagePath("1번 경로")
				   .description("1번 카드 설명")
				   .cardType(CardType.BASIC)
				   .basicList(basicList)
				   .build();
		em.persist(card);
		em.flush();
		//Principal 설정
		Authentication auth = new UsernamePasswordAuthenticationToken(testUser.getEmail(), testUser.getPassword());
		SecurityContextHolder.getContext().setAuthentication(auth);

		//1.이미 카드가 만들어진 경우
		LocalDateTime time = LocalDateTime.of(2023, 9, 26, 10, 10);
		card.setLocalDateTime(time);
		em.flush();
		ResponseEntity<BasicCardRes.BasicCard> response = cardController.selectUserLastBasicCard(
			LocalDateTime.of(2023, 9, 27, 10, 10), auth);
		log.info("Response1 : {}", response);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertThat(response.getBody().getCard().getCreatedDate().equals(time));
		assertThat(response.getBody().getCard().getCardType().equals(CardType.BASIC));

		//2. 카드를 만들 수 있는 사용자
		time = LocalDateTime.of(2023, 8, 26, 10, 10);
		card.setLocalDateTime(time);
		em.flush();
		try {
			ResponseEntity<BasicCardRes.BasicCard> response2 = cardController.selectUserLastBasicCard(LocalDateTime.now(),
		auth);
		} catch (CanCreateException e){
			log.info("Create Response2 Message : {}", e.getMessage());
			log.info("Create Response2 ErrorCode : {}", e.getErrorCode());
			log.info("Create Response2 Status : {}, {}, {}", e.getErrorCode().getStatus(), e.getErrorCode().getCode(), e.getErrorCode().getMessage());
			assertThat(e.getErrorCode().getStatus()).isEqualTo(200);
			assertThat(e.getErrorCode().getCode()).isEqualTo("C008");
		}
		//3. 카드가 만들어 지고 있는 사용자
		time = LocalDateTime.of(2023, 8, 26, 10, 10);
		card.setLocalDateTime(time);
		Card card2 = Card.builder().user(testUser).cardType(CardType.BASIC).build();
		em.persist(card2);
		em.flush();
		try{
			ResponseEntity<BasicCardRes.BasicCard> response3 = cardController.selectUserLastBasicCard(LocalDateTime.now(),
				auth);
		} catch (AlreadyInProgressException e){
			log.info("Create Response3 Message : {}", e.getMessage());
			log.info("Create Response3 ErrorCode : {}", e.getErrorCode());
			log.info("Create Response3 Status : {}, {}, {}", e.getErrorCode().getStatus(), e.getErrorCode().getCode(), e.getErrorCode().getMessage());
			assertThat(e.getErrorCode().getStatus()).isEqualTo(400);
			assertThat(e.getErrorCode().getCode()).isEqualTo("C009");
		}

	}

}
