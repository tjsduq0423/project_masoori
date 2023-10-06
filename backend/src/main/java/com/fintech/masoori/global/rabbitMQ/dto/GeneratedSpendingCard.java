package com.fintech.masoori.global.rabbitMQ.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GeneratedSpendingCard {
	private Long cardId;
	private String name;
	private String imagePath;
	private String description;

	@Builder.Default
	private List<GeneratedSpending> spendings = new ArrayList<>();
}
