package com.fintech.masoori.global.rabbitMQ.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneratedSpending {
	private String keyword;
	private Integer totalAmount;
	private Integer frequency;
}
