package com.fintech.masoori.domain.analytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintech.masoori.domain.analytics.entity.MonthlySpendingAnalytics;

public interface MonthlySpendingAnalyticsRepository extends JpaRepository<MonthlySpendingAnalytics, Long> {
	MonthlySpendingAnalytics save(MonthlySpendingAnalytics monthlySpendingAnalytics);

}
