package com.fintech.masoori.global.rabbitMQ.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class GeneratedChallengeCard {
	private Long cardId;
	private String imagePath;
}
