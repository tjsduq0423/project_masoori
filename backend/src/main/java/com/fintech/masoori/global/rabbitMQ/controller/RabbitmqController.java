package com.fintech.masoori.global.rabbitMQ.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.service.UserService;
import com.fintech.masoori.global.rabbitMQ.dto.AnalyticsReq;
import com.fintech.masoori.global.rabbitMQ.dto.AnalyticsRes;
import com.fintech.masoori.global.rabbitMQ.dto.RabbitTestMessage;
import com.fintech.masoori.global.rabbitMQ.dto.RecommendReq;
import com.fintech.masoori.global.rabbitMQ.service.AnalyticsPubService;
import com.fintech.masoori.global.rabbitMQ.service.AnalyticsSubService;
import com.fintech.masoori.global.rabbitMQ.service.RabbitTestPubService;
import com.fintech.masoori.global.rabbitMQ.service.RecommendPubService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rabbit")
@RequiredArgsConstructor
public class RabbitmqController {
	private final RabbitTestPubService rabbitTestPub;
	private final UserService userService;
	private final AnalyticsPubService analyticsPubService;
	private final RecommendPubService recommendPubService;

	@GetMapping("/pub-test")
	public ResponseEntity<?> testPub() {
		for (int i = 0; i < 10; i++) {
			rabbitTestPub.sendMessage(new RabbitTestMessage(String.valueOf(i), "test", "test"));
		}
		return ResponseEntity.ok().build();
	}

	@PostMapping("/analytics")
	public ResponseEntity<?> monthlyAnalytics(Authentication authentication) {
		User loginUser = loginUser(authentication);
		AnalyticsReq analyticsReq = new AnalyticsReq();
		analyticsReq.setUser(loginUser);
		analyticsPubService.sendMessage(analyticsReq);
		return ResponseEntity.accepted().build();
	}

	@PostMapping("/recommend")
	public ResponseEntity<?> recommendCreditcard(Authentication authentication) {
		User loginUser = loginUser(authentication);
		RecommendReq recommendReq = new RecommendReq();
		recommendReq.setUser(loginUser);
		recommendReq.setLocalDateTime(LocalDateTime.now());
		recommendPubService.sendMessage(recommendReq);
		return ResponseEntity.accepted().build();
	}

	// 현재 로그인한 유저 정보를 찾아온다.
	public User loginUser(Authentication authentication) {
		return userService.findByEmail(authentication.getPrincipal().toString()).get();
	}
}
