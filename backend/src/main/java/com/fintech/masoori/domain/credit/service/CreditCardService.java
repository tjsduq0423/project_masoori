package com.fintech.masoori.domain.credit.service;

import java.time.LocalDateTime;

import com.fintech.masoori.domain.credit.dto.CreditCardRes;
import com.fintech.masoori.domain.credit.dto.UserCreditCardRes;
import com.fintech.masoori.domain.credit.entity.CreditCard;
import com.fintech.masoori.domain.credit.entity.CreditCardUser;
import com.fintech.masoori.global.rabbitMQ.dto.MonthlySpendingAndCreditcard;

public interface CreditCardService {
	/**
	 * 유저-카드 전체 조회
	 */
	UserCreditCardRes selectAll(String userEmail);

	CreditCardRes selectMonth(String userEmail, LocalDateTime time);

	CreditCard selectOne(Long id);

	void saveRecommendedCreditCard(MonthlySpendingAndCreditcard monthlySpendingAndCreditcard);
}
