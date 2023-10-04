package com.fintech.masoori.domain.deal.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.deal.dto.DealsReq;
import com.fintech.masoori.domain.deal.repository.DealRepository;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.exception.UserNotFoundException;
import com.fintech.masoori.domain.user.repository.UserRepository;
import com.fintech.masoori.global.rabbitMQ.dto.Transaction;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DealServiceImpl implements DealService {
	private final DealRepository dealRepository;
	private final UserRepository userRepository;

	@Override
	public List<Transaction> findDealsByUserAndDateGreaterThanAndDateLessThan(User user, LocalDateTime startTime,
		LocalDateTime endTime) {
		return dealRepository.findDealsByUserAndDateGreaterThanAndDateLessThan(user, startTime, endTime)
		                     .stream()
		                     .map(Transaction::new)
		                     .toList();
	}

	@Override
	@Transactional
	public void setDealListForUser(DealsReq dealsReq) {
		User user = userRepository.findById(dealsReq.getUserId())
		                          .orElseThrow(() -> new UserNotFoundException("User is Not Found"));
		dealsReq.getTransactionList().stream().map(Transaction::toEntity).forEach(e -> {
			e.setUser(user);
			dealRepository.save(e);
		});

	}
}
