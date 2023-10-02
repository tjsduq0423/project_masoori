package com.fintech.masoori.global.rabbitMQ.dto;

import java.time.LocalDateTime;

import com.fintech.masoori.domain.deal.entity.Deal;
import com.fintech.masoori.domain.user.entity.User;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AnalyticsReq {
	// 유저id와 거래내역 리스트를 보낸다.
	private int id;
	private int userId;
	private User user;

	private LocalDateTime date;

	private String content;

	private Integer amount;

	private String dealPlaceName;
}
