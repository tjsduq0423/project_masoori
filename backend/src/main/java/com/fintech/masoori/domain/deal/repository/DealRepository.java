package com.fintech.masoori.domain.deal.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintech.masoori.domain.deal.entity.Deal;
import com.fintech.masoori.domain.user.entity.User;

public interface DealRepository extends JpaRepository<Deal, Long> {
	List<Deal> findDealsByUserAndDateGreaterThanAndDateLessThan(User user, LocalDateTime startTime,
		LocalDateTime endTime);
}
