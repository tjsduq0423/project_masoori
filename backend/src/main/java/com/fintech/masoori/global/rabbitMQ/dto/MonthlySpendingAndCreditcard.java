package com.fintech.masoori.global.rabbitMQ.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fintech.masoori.domain.analytics.entity.MonthlySpendingAnalytics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MonthlySpendingAndCreditcard {
	// 분석된 월간 소비내용과 추천된 신용카드 리스트를 받는다.
	private Long userId;
	private String date;
	private List<RecommendedCreditCard> creditCardList;
	@Builder.Default
	private List<GeneratedSpending> spendings = new ArrayList<>();

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class RecommendedCreditCard {
		private Long creditCardId;
		private String reason;
	}
}
