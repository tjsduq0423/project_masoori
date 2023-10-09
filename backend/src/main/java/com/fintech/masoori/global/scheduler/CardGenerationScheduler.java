package com.fintech.masoori.global.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fintech.masoori.domain.card.service.CardService;
import com.fintech.masoori.domain.deal.service.DealService;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.service.UserService;
import com.fintech.masoori.global.rabbitMQ.dto.AnalyticsRequestMessage;
import com.fintech.masoori.global.rabbitMQ.dto.Transaction;
import com.fintech.masoori.global.rabbitMQ.service.AnalyticsPubService;
import com.fintech.masoori.global.rabbitMQ.service.ChallengePubService;
import com.fintech.masoori.global.rabbitMQ.service.SpendingPubService;
import com.fintech.masoori.global.util.CalcDate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
@EnableAsync
public class CardGenerationScheduler {
	private final UserService userService;
	private final DealService dealService;
	private final ChallengePubService challengePubService;
	private final SpendingPubService spendingPubService;
	private final CardService cardService;
	private final AnalyticsPubService analyticsPubService;

	/**
	 * 추천 크레딧 Card 생성 요청 - 월 1회 -> 매월 1일 새벽 1시
	 */
	@Async
	@Scheduled(cron = "0 0 1 1 * *")
	public void creditCardGenerateMonthly() {
		log.info("");
		List<User> userList = userService.findUsersByIsAuthenticated(true);
		for (User user : userList) {
			CalcDate.StartEndDate startEndDate = CalcDate.calcLastMonth();
			List<Transaction> transactionList = dealService.findDealsByUserAndDateGreaterThanAndDateLessThan(user,
				startEndDate.getStartDate(), startEndDate.getEndDate());
			AnalyticsRequestMessage message = AnalyticsRequestMessage.builder()
			                                                         .userId(user.getId())
			                                                         .userMonthlyTransactionList(transactionList)
			                                                         .build();
			analyticsPubService.sendMessage(message);
		}
	}

	/**
	 챌린지 생성 요청 - 매월 첫 날. 0시 0분
	 */
	@Async
	@Scheduled(cron = "0 0 0 1  * *")
	public void challengeGenerateWeelky() {
		List<User> userList = userService.findUsersByIsAuthenticated(true);
		for (User user : userList) {
			cardService.createChallengeCard(user.getEmail());
		}
	}

	/**
	 * 소비 카드 -> 주 1회 주 시작 하는 일요일 정각.
	 */
	@Async
	@Scheduled(cron = "0 0 0 * * 0")
	public void spendingAnalytics() {
		List<User> userList = userService.findUsersByIsAuthenticated(true);
		for (User user : userList) {
			cardService.createSpendingCard(user.getEmail());
		}
	}
}
