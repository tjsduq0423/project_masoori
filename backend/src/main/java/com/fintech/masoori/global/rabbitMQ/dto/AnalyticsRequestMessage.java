package com.fintech.masoori.global.rabbitMQ.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AnalyticsRequestMessage {
	// 유저id와 거래내역 리스트를 보낸다.
	private Long userId;
	@Builder.Default
	private List<Transaction> transactionList = new ArrayList<>();
}
