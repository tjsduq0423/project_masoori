package com.fintech.masoori.global.rabbitMQ.dto;

import java.time.LocalDateTime;

import com.fintech.masoori.domain.deal.entity.Deal;

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
public class Transaction {
	private Long id;
	private LocalDateTime date;
	private String content;
	private Integer amount;
	private String dealPlaceName;

	public Transaction(Deal deal) {
		this.id = deal.getId();
		this.date = deal.getDate();
		this.content = deal.getContent();
		this.amount = deal.getAmount();
		this.dealPlaceName = deal.getDealPlaceName();
	}

	public Deal toEntity() {
		return Deal.builder()
		           .id(this.id)
		           .date(this.date)
		           .content(this.content)
		           .amount(this.amount)
		           .dealPlaceName(this.dealPlaceName)
		           .build();
	}
}
