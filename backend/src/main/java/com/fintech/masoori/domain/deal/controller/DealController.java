package com.fintech.masoori.domain.deal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.masoori.domain.deal.dto.DealsReq;
import com.fintech.masoori.domain.deal.service.DealService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/deal")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Deal", description = "소비자 거래내역 더미데이터 추가 api")
public class DealController {
	private final DealService dealService;

	@Operation(summary = "사용자 거래내역 추가 by id And DealList", description = "사용자의 거래내역을 추가한다.")
	@PostMapping("/register")
	public ResponseEntity<?> setttingDealList(@RequestBody DealsReq dealsReq) {
		log.info(dealsReq.toString());
		dealService.setDealListForUser(dealsReq);
		return ResponseEntity.ok().build();
	}

}
