package com.fintech.masoori.domain.deal.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintech.masoori.domain.deal.entity.Deal;

public interface DealRepository extends JpaRepository<Deal, Long> {
	List<Deal> findDealsByUserAndDateGreaterThanAndDateLessThan(LocalDateTime startTime, LocalDateTime endTime);
}
