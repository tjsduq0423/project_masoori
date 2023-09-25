package com.fintech.masoori.domain.analytics.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.masoori.domain.analytics.dto.MonthlySpendingAnalyticsRes;
import com.fintech.masoori.domain.analytics.service.MonthlySpendingAnalyticsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/analytics")
@RequiredArgsConstructor
@Tag(name = "Analytics", description = "월간 소비 분석 API")
public class MonthlySpendingAnalyticsController {
	private final MonthlySpendingAnalyticsService monthlySpendingAnalyticsService;

	//월간 소비 분석 전체 조회
	@Operation(summary = "월간 소비 분석 전체 조회 API", description = "유저의 모든 월간 분석 내용을 조회한다.")
	@GetMapping("/month")
	public ResponseEntity<MonthlySpendingAnalyticsRes> selectConsumeCard(Principal principal) {
		MonthlySpendingAnalyticsRes monthlySpendingAnalyticsRes = monthlySpendingAnalyticsService.selectAll(
			principal.getName());
		return ResponseEntity.ok().body(monthlySpendingAnalyticsRes);
	}

}
