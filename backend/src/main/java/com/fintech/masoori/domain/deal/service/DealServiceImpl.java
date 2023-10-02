package com.fintech.masoori.domain.deal.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fintech.masoori.domain.deal.repository.DealRepository;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.global.rabbitMQ.dto.Transaction;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {
	private final DealRepository dealRepository;

	@Override
	public List<Transaction> findDealsByUserAndDateGreaterThanAndDateLessThan(User user, LocalDateTime startTime,
		LocalDateTime endTime) {
		return dealRepository.findDealsByUserAndDateGreaterThanAndDateLessThan(user, startTime, endTime)
		                     .stream()
		                     .map(Transaction::new)
		                     .toList();
	}
}
