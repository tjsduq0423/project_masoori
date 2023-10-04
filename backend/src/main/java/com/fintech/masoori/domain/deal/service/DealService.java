package com.fintech.masoori.domain.deal.service;

import java.time.LocalDateTime;
import java.util.List;

import com.fintech.masoori.domain.deal.dto.DealsReq;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.global.rabbitMQ.dto.Transaction;

public interface DealService {
	List<Transaction> findDealsByUserAndDateGreaterThanAndDateLessThan(User user, LocalDateTime startTime,
		LocalDateTime endTime);

	void setDealListForUser(DealsReq dealsReq);
}
