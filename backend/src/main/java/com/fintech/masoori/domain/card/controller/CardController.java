package com.fintech.masoori.domain.card.controller;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.masoori.domain.card.dto.BasicCardRes;
import com.fintech.masoori.domain.card.dto.ChallengeCardRes;
import com.fintech.masoori.domain.card.dto.SelectBasicCardReq;
import com.fintech.masoori.domain.card.dto.SelectChallengeCardReq;
import com.fintech.masoori.domain.card.service.CardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/card")
@RequiredArgsConstructor
@Tag(name = "Card", description = "소비카드 및 챌린지카드 API")
public class CardController {
	private final CardService cardService;

	//소비 카드 생성
	@Operation(summary = "소비 카드 생성 API", description = "사용자 최초 등록 시 소비카드를 생성한다.")
	@PostMapping("/consume")
	public ResponseEntity<?> createConsumeCard(Principal principal) {
		return ResponseEntity.ok().build();
	}

	//소비 카드 조회
	@Operation(summary = "소비 카드 조회 API", description = "유저의 모든 소비카드 조회, 연,월을 기준으로 페이지네이션")
	@GetMapping("/consume")
	public ResponseEntity<BasicCardRes.BasicCard> selectConsumeCard(
		@Parameter(description = "조회 시작일, 조회 개수")
		@RequestBody SelectBasicCardReq basicCardReq, Principal principal) {
		return ResponseEntity.ok().build();
	}

	//소비 카드 상세 조회
	@Operation(summary = "소비 카드 상세 조회 API", description = "소비카드 한 장을 조회한다.")
	@GetMapping("/consume/{id}")
	public ResponseEntity<BasicCardRes> detailConsumeCard(
		@Parameter(description = "소비카드 id", required = true, example = "1")
		@PathVariable Long id, Principal principal) {
		return ResponseEntity.ok().build();
	}

	//챌린지 조회
	@Operation(summary = "챌린지 조회 API", description = "유저에게 할당되어 있는 챌린지카드와 챌린지를 연,월일을 통해 조회한다.")
	@GetMapping("/challenge")
	public ResponseEntity<ChallengeCardRes.ChallengeCard> selectChallenge(
		@Parameter(description = "조회할 연,월(Date 객체)", required = true, example = "2023-09-14T15:30:45")
		@RequestBody LocalDateTime time, Principal principal) {
		return ResponseEntity.ok().build();
	}

	//챌린지 카드 조회
	@Operation(summary = "챌린지 카드 조회 API", description = "조회 시작일과 종료일을 통해 유저의 챌린지 카드들을 조회")
	@GetMapping("/challengecard")
	public ResponseEntity<ChallengeCardRes> selectChallengeCard(
		@RequestBody SelectChallengeCardReq challengeCardReq, Principal principal) {
		return ResponseEntity.ok().build();
	}

}
