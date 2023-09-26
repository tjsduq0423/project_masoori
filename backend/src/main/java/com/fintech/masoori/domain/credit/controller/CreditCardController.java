package com.fintech.masoori.domain.credit.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.masoori.domain.analytics.dto.MonthlySpendingAnalyticsRes;
import com.fintech.masoori.domain.analytics.service.MonthlySpendingAnalyticsService;
import com.fintech.masoori.domain.credit.dto.CreditCardReq;
import com.fintech.masoori.domain.credit.dto.CreditCardRes;
import com.fintech.masoori.domain.credit.dto.MonthlyInfoRes;
import com.fintech.masoori.domain.credit.entity.CreditCard;
import com.fintech.masoori.domain.credit.service.CreditCardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/creditcard")
@Tag(name = "creaditCard", description = "신용카드 API")
@RequiredArgsConstructor
public class CreditCardController {

	private final CreditCardService creditCardService;
	private final MonthlySpendingAnalyticsService monthlySpendingAnalyticsService;

	@Operation(summary = "사용자 추천 카드 조회 API")
	@GetMapping("")
	public ResponseEntity<MonthlyInfoRes> selectMonthCreditCardUser(
		@Parameter(description = "조회할 연, 월", required = true, example = "2023-09-14T15:30:45")
		@RequestBody CreditCardReq creditCardReq,
		Principal principal) {
		CreditCardRes creditCardRes = creditCardService.selectMonth(principal.getName(), creditCardReq.getTime());
		MonthlySpendingAnalyticsRes monthlySpendingAnalyticsRes = monthlySpendingAnalyticsService.selectAll(
			principal.getName(), creditCardReq.getTime());
		MonthlyInfoRes monthlyInfoRes = MonthlyInfoRes.builder()
													  .creditCardRes(creditCardRes)
													  .monthlySpendingAnalyticsRes(monthlySpendingAnalyticsRes)
													  .build();
		return ResponseEntity.ok(monthlyInfoRes);
	}

	@Operation(summary = "사용자 추천 카드 전체 조회 API")
	@GetMapping("/all")
	public ResponseEntity<CreditCardRes> selectAllCreditCardUser(Principal principal) {
		CreditCardRes creditCardList = creditCardService.selectAll(principal.getName());
		return ResponseEntity.ok(creditCardList);
	}

	@Operation(summary = "카드 상세 정보 조회 API")
	@GetMapping("/{cardId}")
	public ResponseEntity<CreditCardRes.CreditCard> selectOne(
		@Parameter(name = "cardId", description = "카드 아이디", required = true, example = "1", in = ParameterIn.PATH) @PathVariable Long cardId) {
		CreditCard creditCard = creditCardService.selectOne(cardId);
		CreditCardRes.CreditCard creditCard1 = new CreditCardRes.CreditCard(creditCard);
		return ResponseEntity.ok(creditCard1);
	}

}
