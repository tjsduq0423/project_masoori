package com.fintech.masoori.global.rabbitMQ.dto;

import java.util.List;

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
	private Long userId;
	private String name;
	private String imagePath;
	private String description;
	private List<GeneratedChallenge> challenges;
}
