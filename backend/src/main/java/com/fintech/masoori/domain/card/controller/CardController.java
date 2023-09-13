package com.fintech.masoori.domain.card.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.masoori.domain.card.service.CardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/card")
@RequiredArgsConstructor
public class CardController {
	private final CardService cardService;

	//소비 카드 생성
	@PostMapping("/consume")
	public ResponseEntity<?> createConsumeCard() {
		return ResponseEntity.ok(200);
	}

	//소비 카드 조회
	@GetMapping("/consume")
	public ResponseEntity<?> selectConsumeCard() {
		return ResponseEntity.ok(200);
	}

	//소비 카드 상세 조회
	@GetMapping("/consume/{id}")
	public ResponseEntity<?> detailConsumeCard(@PathVariable Long id) {
		return ResponseEntity.ok(200);
	}

	//챌린지 조회
	@GetMapping("/challenge")
	public ResponseEntity<?> selectChallenge() {
		return ResponseEntity.ok(200);
	}

	//챌린지 카드 조회
	@GetMapping("/challengecard")
	public ResponseEntity<?> selectChallengeCard() {
		return ResponseEntity.ok(200);
	}

}
