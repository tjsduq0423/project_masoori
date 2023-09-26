package com.fintech.masoori.domain.credit.controller;

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
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.analytics.entity.MonthlySpendingAnalytics;
import com.fintech.masoori.domain.analytics.repository.MonthlySpendingAnalyticsRepository;
import com.fintech.masoori.domain.credit.dto.CreditCardReq;
import com.fintech.masoori.domain.credit.dto.MonthlyInfoRes;
import com.fintech.masoori.domain.credit.entity.CreditCard;
import com.fintech.masoori.domain.credit.entity.CreditCardUser;
import com.fintech.masoori.domain.credit.repository.CreditCardRepository;
import com.fintech.masoori.domain.credit.repository.CreditCardUserRepository;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.repository.UserRepository;
import com.fintech.masoori.global.oauth.ProviderType;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
@Slf4j
class CreditCardControllerTest {

	@Autowired
	private MonthlySpendingAnalyticsRepository monthlySpendingAnalyticsRepository;
	@Autowired
	private CreditCardRepository creditCardRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CreditCardUserRepository creditCardUserRepository;
	@Autowired
	private CreditCardController creditCardController;

	private User testUser;
	private List<CreditCard> creditCardList;
	private List<CreditCardUser> creditCardUserList;
	private List<MonthlySpendingAnalytics> monthlySpendingAnalyticsList;

	@BeforeEach
	void setUp() {
		// 사용자 생성 및 저장
		testUser = User.builder()
					   .email("test123232@gmail.com")
					   .password("123")
					   .providerType(ProviderType.LOCAL)
					   .build();
		userRepository.save(testUser);

		// 테스트 데이터 생성
		creditCardList = new ArrayList<>();
		creditCardUserList = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			CreditCard creditCard = CreditCard.builder()
											  .name(i + "번 카드")
											  .company(i + "회사")
											  .build();
			creditCardList.add(creditCard);
			if (i % 2 == 0) {
				CreditCardUser creditCardUser = CreditCardUser.builder()
															  .user(testUser)
															  .creditCard(creditCard)
															  .build();
				creditCardUserList.add(creditCardUser);
			}
		}
		creditCardRepository.saveAll(creditCardList);
		creditCardUserRepository.saveAll(creditCardUserList);

		monthlySpendingAnalyticsList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			MonthlySpendingAnalytics monthlySpendingAnalytics = MonthlySpendingAnalytics.builder()
																						.user(testUser)
																						.category(i + "번 카테고리")
																						.cost(i * 10000)
																						.build();
			monthlySpendingAnalyticsList.add(monthlySpendingAnalytics);
		}
		monthlySpendingAnalyticsRepository.saveAll(monthlySpendingAnalyticsList);
	}

	@Test
	void 사용자_월간_소비분석_및_추천카드() {
		//Principal 설정
		Authentication auth = new UsernamePasswordAuthenticationToken(testUser.getEmail(), testUser.getPassword());
		SecurityContextHolder.getContext().setAuthentication(auth);

		CreditCardReq creditCardReq = CreditCardReq.builder().time(LocalDateTime.now()).build();

		ResponseEntity<MonthlyInfoRes> response = creditCardController.selectMonthCreditCardUser(
			creditCardReq,
			auth);

		log.info("Response : {}", response);

		// 테스트 결과를 검증
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertThat(response.getBody().getCreditCardRes().getCreditCardList().get(0).equals(creditCardList.get(0)));
		assertThat(response.getBody()
						   .getMonthlySpendingAnalyticsRes()
						   .getMonthlySpendingAnalyticsList()
						   .equals(monthlySpendingAnalyticsList.get(0)));
		assertThat(response.getBody()
						   .getCreditCardRes()
						   .getCreditCardList()
						   .get(0)
						   .equals(creditCardUserList.get(0).getCreditCard()));

		//Principal 리셋
		SecurityContextHolder.clearContext();
	}
}
