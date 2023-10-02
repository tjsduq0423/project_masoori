package com.fintech.masoori.global.rabbitMQ.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpendingRequestMessage {
	private String id;
}
