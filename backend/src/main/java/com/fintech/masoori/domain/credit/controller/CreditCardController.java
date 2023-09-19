package com.fintech.masoori.domain.credit.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.masoori.domain.credit.dto.CreditCardRes;
import com.fintech.masoori.domain.credit.service.CreditCardService;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/creditcard")
@Tag(name = "creaditCard", description = "신용카드 API")
@RequiredArgsConstructor
public class CreditCardController {

	private final UserService userService;
	private final CreditCardService creditCardService;

	@Operation(summary = "사용자한테 추천해주는 카드 리스트")
	@GetMapping("")
	public ResponseEntity<?> selectAllCreditCardUser(Authentication authentication) {
		User loginUser = userService.findByEmail(authentication.getPrincipal().toString()).get();
		CreditCardRes creditCardRes = creditCardService.selectAll(loginUser.getEmail());
		return ResponseEntity.ok(creditCardRes);
	}

	

}
