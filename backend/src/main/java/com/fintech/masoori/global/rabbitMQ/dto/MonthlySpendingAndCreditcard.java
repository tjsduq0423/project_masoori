package com.fintech.masoori.global.rabbitMQ.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fintech.masoori.domain.analytics.entity.MonthlySpendingAnalytics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MonthlySpendingAndCreditcard {
	// 분석된 월간 소비내용과 추천된 신용카드 리스트를 받는다.
	private Long userId;
	private List<MonthlySpending> monthlySpendingList;
	private List<RecommendedCreditCard> creditCardList;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MonthlySpending {
		private String category;
		private Integer cost;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class RecommendedCreditCard {
		private Long creditCardId;
		private String reason;
	}
}
