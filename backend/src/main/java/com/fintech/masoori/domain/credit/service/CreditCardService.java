package com.fintech.masoori.domain.credit.service;

import java.util.List;

import com.fintech.masoori.domain.credit.dto.CreditCardRes;

public interface CreditCardService {
	/**
	 * 유저-카드 전체 조회
	 */
	List<CreditCardRes.CreditCard> selectAll(String userEmail);
}
