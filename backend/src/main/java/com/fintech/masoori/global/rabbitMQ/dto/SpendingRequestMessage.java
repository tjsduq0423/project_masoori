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
public class SpendingRequestMessage {
	private Long userId;
	@Builder.Default
	private List<UserDealingData> userDealingDataList = new ArrayList<>();

	public static class UserDealingData {
		private Long id;
	}
}
