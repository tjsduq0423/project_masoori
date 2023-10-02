package com.fintech.masoori.global.rabbitMQ.dto;

import java.time.LocalDateTime;

import com.fintech.masoori.domain.user.entity.User;

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
public class RecommendReq {
	private int id;
	private User user;
	private LocalDateTime localDateTime;
}