package com.fintech.masoori.global.rabbitMQ.dto;

import java.util.List;

import com.fintech.masoori.domain.credit.entity.CreditCardUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecommendRes {
	private int id;
	private List<CreditCardUser> creditCardUserList;

}
