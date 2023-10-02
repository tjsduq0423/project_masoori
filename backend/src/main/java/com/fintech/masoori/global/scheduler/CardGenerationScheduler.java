package com.fintech.masoori.global.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fintech.masoori.domain.deal.service.DealService;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.service.UserService;
import com.fintech.masoori.global.rabbitMQ.dto.ChallengeRequestMessage;
import com.fintech.masoori.global.rabbitMQ.dto.SpendingRequestMessage;
import com.fintech.masoori.global.rabbitMQ.dto.Transaction;
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

	/**
	 * 추천 크레딧 Card 생성 요청 - 주 1회 오늘 날짜를 기준으로 한달 치 데이터를 기반으로
	 */
	@Async
	@Scheduled(cron = "0 0 1 * * 0")
	public void creditCardGenerateWeekly() {
		log.info("");
		// 모든 유저에 대해 유저의 연동 여부를 체크하여 true인 유저에 대해
		// 프로세스에 필요하다고 생각되는 Dto
		// 추천된 creditCard List 등록 유저 객체를 조회 해서 해당 유저의 크레딧 카드 유저 객체를 생성 추가하여 등록
	}

	/**
	 챌린지 생성 요청 - 주 1회 언제? 주 시작 -> 매주 일요일 새벽 1시
	 */
	@Async
	@Scheduled(cron = "0 0 1 * * 0")
	public void challengeGenerateWeelky() {
		List<User> userList = userService.findUsersByIsAuthenticated(true);
		for (User user : userList) {
			CalcDate.StartEndDate startEndDate = CalcDate.calcLastWeek();
			List<Transaction> transactionList = dealService.findDealsByUserAndDateGreaterThanAndDateLessThan(user,
				startEndDate.getStartDate(), startEndDate.getEndDate());
			ChallengeRequestMessage message = ChallengeRequestMessage.builder()
			                                                         .userId(user.getId())
			                                                         .userMonthlyTransactionList(transactionList)
			                                                         .build();
			challengePubService.sendMessage(message);
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
			CalcDate.StartEndDate startEndDate = CalcDate.calcLastWeek();
			List<Transaction> transactionList = dealService.findDealsByUserAndDateGreaterThanAndDateLessThan(user,
				startEndDate.getStartDate(), startEndDate.getEndDate());
			SpendingRequestMessage message = SpendingRequestMessage.builder()
			                                                       .userId(user.getId())
			                                                       .userWeeklyTransactionList(transactionList)
			                                                       .build();
			spendingPubService.sendMessage(message);
		}

	}

	/**
	 * 유저에 대한 월간 분석을 실시함. 웛 1회 -> 매월 첫번째 월요일 실행
	 */
	@Async
	@Scheduled(cron = "0 0 0 * * 1#1")
	public void monthlySpendingAnalyticsGenerate() {
		log.info("");
	}
}
