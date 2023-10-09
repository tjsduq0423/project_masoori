package com.fintech.masoori.global.util;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.masoori.domain.card.service.CardService;
import com.fintech.masoori.domain.deal.service.DealService;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.repository.UserRepository;
import com.fintech.masoori.global.rabbitMQ.dto.AnalyticsRequestMessage;
import com.fintech.masoori.global.rabbitMQ.dto.Transaction;
import com.fintech.masoori.global.rabbitMQ.service.AnalyticsPubService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class TestController {
	private final UserRepository userRepository;
	private final CardService cardService;
	private final DealService dealService;
	private final AnalyticsPubService analyticsPubService;

	// 날짜 기준으로 그 주의 카드 추천.
	// 거래내역이 해당 날짜의 전주 에 위치하지 않으면 err
	@GetMapping("/test/analytics")
	public ResponseEntity<?> cardTestAPI(Principal principal, @RequestParam LocalDateTime date) {
		User user = userRepository.findUserByEmail(principal.getName());
		CalcDate.StartEndDate startEndDate = CalcDate.calcLastMonth(date);
		List<Transaction> transactionList = dealService.findDealsByUserAndDateGreaterThanAndDateLessThan(user,
			startEndDate.getStartDate(), startEndDate.getEndDate());
		String tempDate = date.getYear()+"/"+date.getMonthValue()+"/"+date.getDayOfMonth();
		AnalyticsRequestMessage message = AnalyticsRequestMessage.builder()
		                                                         .userId(user.getId())
																 .date(tempDate)
		                                                         .userMonthlyTransactionList(transactionList)
		                                                         .build();
		analyticsPubService.sendMessage(message);
		return ResponseEntity.ok().build();
	}

	// 챌린지 날짜에 맞게 생성함 이미지 생성까지. 챌린지 생서을 위해 필요한거 없음
	@GetMapping("/test/challengeCard")
	public ResponseEntity<?> challengeCardTestAPI(Principal principal, @RequestParam LocalDateTime date) {
		cardService.createChallengeCard(principal.getName(), date);
		return ResponseEntity.ok().build();
	}

	// 챌린지를 날짜에 맞게 내용으로 해당 챌린지 카드에 삽입 챌린지 카드ID가 존재해야함. 그리고 챌린지는 선행조건없음
	@GetMapping("/test/challenge")
	public ResponseEntity<?> challengeTestAPI(@RequestParam LocalDateTime date, @RequestParam Long cardId,
		@RequestParam String achievementCondition) {
		cardService.addChallenge(cardId, achievementCondition, date);
		return ResponseEntity.ok().build();
	}

	// 소비 카드 생성 날짜기준-> 해당 날짜 기준 전주 거래내역없으면 안댐.
	@GetMapping("/test/spending")
	public ResponseEntity<?> spendingTestAPI(Principal principal, @RequestParam LocalDateTime date) {
		cardService.createSpendingCard(principal.getName(), date);
		return ResponseEntity.ok().build();
	}

}
