package com.fintech.masoori.domain.analytics.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fintech.masoori.domain.analytics.entity.MonthlySpendingAnalytics;

public interface MonthlySpendingAnalyticsRepository extends JpaRepository<MonthlySpendingAnalytics, Long> {
	MonthlySpendingAnalytics save(MonthlySpendingAnalytics monthlySpendingAnalytics);

	@Query("SELECT m FROM MonthlySpendingAnalytics m WHERE m.user.id = :userId AND m.createdDate >= :startDate AND m.createdDate <= :endDate")
	List<MonthlySpendingAnalytics> findMonthlySpendingAnalyticsByUserId(@Param("userId") Long userId,
		@Param("startDate") LocalDateTime startDate,
		@Param("endDate") LocalDateTime endDate);

}
