package com.fintech.masoori.global.Scheduler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fintech.masoori.global.rabbitMQ.service.WeeklyPubService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
@EnableAsync
public class Scheduler {

	private final WeeklyPubService weeklyPubService;
	// 추천 크레딧 Card 생성 요청 - 주 1회 오늘 날짜를 기준으로 한달 치 데이터를 기반으로
	@Async
	@Scheduled(fixedRate = 3000)
	public void creditCardGenerateWeekly() {
		// 모든 유저에 대해 유저의 연동 여부를 체크하여 true인 유저에 대해
		// 프로세스에 필요하다고 생각되는 Dto
		// 추천된 creditCard List 등록 유저 객체를 조회 해서 해당 유저의 크레딧 카드 유저 객체를 생성 추가하여 등록

	}

	/**
	 챌린지 생성 요청 - 주 1회 언제? 주 시작 -> 매주 일요일 새벽 1시
 	 */
	@Async
	@Scheduled(fixedRate = 3000)
	public void challengeGenerateWeelky() {
		// 모든 유저에 대해 유저의 연동 여부를 체크하여 true인 유저에 대해
		// 유저 카드를 등록하고 해당 카드의 type을 챌린지로 지정 - 챌린지 객체 리스트에 받아온 챌린지 객체를 저장.
	}

	/**
	 * 소비 카드 -> 주기적으로 연동된 유저에 대해 소비 분석 데이터 생성 요청!
	 */
	@Async
	@Scheduled(fixedRate = 3000)
	public void spendingAnalytics() {
		// 연동 여부가 true 인 유저에 대해 유저카드를 등록하고 basic으로 지정 소비 엔티티를 생성해서 append하고 저장.
		// 실제로 여기서는 pub service를 주입받아 실행할 뿐 실제 동작은 불가능함.
		//
	}

	/**
	 * 유저에 대한 월간 분석을 실시함. 웛 1회 -> 매달 월요일에 실행.
	 */
	@Async
	@Scheduled(fixedRate = 3000)
	public void monthlySpendingAnalyticsGenerate() {

	}

}
