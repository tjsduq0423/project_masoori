package com.fintech.masoori.domain.analytics.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fintech.masoori.domain.analytics.entity.MonthlySpendingAnalytics;

public interface MonthlySpendingAnalyticsRepository extends JpaRepository<MonthlySpendingAnalytics, Long> {

	@Query("SELECT m FROM MonthlySpendingAnalytics m WHERE m.user.id = :userId AND m.createdDate >= :startDate AND m.createdDate <= :endDate")
	List<MonthlySpendingAnalytics> findMonthlySpendingAnalyticsByUserId(@Param("userId") Long userId,
		@Param("startDate") LocalDateTime startDate,
		@Param("endDate") LocalDateTime endDate);

	@Modifying
	@Query("UPDATE MonthlySpendingAnalytics m SET m.createdDate = :date WHERE m.user.id = :userId AND m.month = :month AND m.year = :year")
	void updateCreatedDate(@Param("date") LocalDateTime date, @Param("userId") long userId, @Param("month") int month, @Param("year") int year);
}
