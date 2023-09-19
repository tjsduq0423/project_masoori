package com.fintech.masoori.domain.deal.service;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.WeekFields;

import org.springframework.stereotype.Service;

import com.fintech.masoori.domain.deal.entity.Deal;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {
	private final UserRepository userRepository;

	@Override
	public Integer getMonthlySpending(String email) {
		User user = userRepository.findUserByEmail(email);
		return user.getDealList()
		           .stream()
		           .filter(e -> e.getDate().getYear() == LocalDate.now().getYear()
			           && e.getDate().getMonth() == LocalDate.now().getMonth())
		           .map(Deal::getAmount)
		           .reduce(0, Integer::sum);
	}

	@Override
	public Integer getWeeklySpending(String email) {
		User user = userRepository.findUserByEmail(email);
		LocalDate now = LocalDate.now();
		return user.getDealList()
		           .stream()
		           .filter(e -> e.getDate().getYear() == now.getYear()
			           && e.getDate().get(WeekFields.ISO.weekOfWeekBasedYear()) == now.get(
			           WeekFields.ISO.weekOfWeekBasedYear()))
		           .map(Deal::getAmount)
		           .reduce(0, Integer::sum);
	}

	@Override
	public Integer getDailySpending(String email) {
		User user = userRepository.findUserByEmail(email);
		LocalDate now = LocalDate.now();
		return user.getDealList()
		           .stream()
		           .filter(e -> e.getDate().isEqual(ChronoLocalDateTime.from(now)))
		           .map(Deal::getAmount)
		           .reduce(0, Integer::sum);
	}
}
