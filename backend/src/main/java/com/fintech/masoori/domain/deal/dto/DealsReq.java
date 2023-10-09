package com.fintech.masoori.domain.deal.dto;

import java.util.List;

import com.fintech.masoori.global.rabbitMQ.dto.Transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DealsReq {
	private Long userId;
	private List<Transaction> transactionList;
}
