package com.fintech.masoori.global.rabbitMQ.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ChallengeRequestMessage {
	private Long userId;
	@Builder.Default
	private List<UserSpendingCardData> userSpendingDataList = new ArrayList<>();

	public static class UserSpendingCardData {
		private Long id;
	}
}
