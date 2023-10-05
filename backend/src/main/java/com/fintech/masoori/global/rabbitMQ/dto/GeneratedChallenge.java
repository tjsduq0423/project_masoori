package com.fintech.masoori.global.rabbitMQ.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneratedChallenge {
	private String name;
	private String achievementCondition;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
}
