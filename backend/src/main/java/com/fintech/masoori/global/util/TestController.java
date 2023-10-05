package com.fintech.masoori.global.util;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@GetMapping("/test")
	public ResponseEntity<?> testAPI() {
		cardService.createSpendingCard("tjsduq0423@naver.com");
		return ResponseEntity.ok().build();
	}

	@GetMapping("/test/card")
	public ResponseEntity<?> cardTestAPI() {
		User user = userRepository.findUserByEmail("ssafy2@gmail.com");
		CalcDate.StartEndDate startEndDate = CalcDate.calcLastWeek();
		List<Transaction> transactionList = dealService.findDealsByUserAndDateGreaterThanAndDateLessThan(user,
			startEndDate.getStartDate(), startEndDate.getEndDate());
		AnalyticsRequestMessage message = AnalyticsRequestMessage.builder()
		                                                         .userId(user.getId())
		                                                         .userWeeklyTransactionList(transactionList)
		                                                         .build();
		analyticsPubService.sendMessage(message);
		return ResponseEntity.ok().build();
	}
}
