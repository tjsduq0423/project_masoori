package com.fintech.masoori.domain.card.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.masoori.domain.card.dto.BasicCardRes;
import com.fintech.masoori.domain.card.dto.ChallengeCardRes;
import com.fintech.masoori.domain.card.dto.RangeCardReq;
import com.fintech.masoori.domain.card.service.CardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/card")
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

	//소비 카드 범위 조회
	@Operation(summary = "소비 카드 범위 조회 API", description = "유저의 소비 카드를 연, 월을 기준으로 조회")
	@GetMapping("/consume")
	public ResponseEntity<BasicCardRes> selectConsumeCard(
		@Parameter(description = "조회 시작일, 조회 개수")
		@RequestBody RangeCardReq basicCardReq, Principal principal) {
		BasicCardRes basicCardList = cardService.selectRangeBasicCard(principal.getName(), basicCardReq.getStartTime(),
			basicCardReq.getEndTime());
		return ResponseEntity.ok(basicCardList);
	}

	//소비 카드 조회
	@Operation(summary = "소비 카드 조회 API", description = "소비 카드 한 장을 조회한다.")
	@GetMapping("/consume/{id}")
	public ResponseEntity<BasicCardRes.BasicCard> detailConsumeCard(
		@Parameter(name = "cardId", description = "소비카드 id", required = true, example = "1", in = ParameterIn.PATH)
		@PathVariable Long id, Principal principal) {
		BasicCardRes.BasicCard basicCard = cardService.selectBasicCard(principal.getName(), id);
		return ResponseEntity.ok(basicCard);
	}

	//챌린지 카드 범위 조회
	@Operation(summary = "챌린지 카드 범위 조회 API", description = "유저의 챌린지 카드를 연, 월을 기준으로 조회")
	@GetMapping("/challenge")
	public ResponseEntity<ChallengeCardRes> selectChallenge(
		@Parameter(description = "조회할 연,월(Date 객체)", required = true, example = "2023-09-14T15:30:45")
		@RequestBody RangeCardReq challengeCardReq, Principal principal) {
		ChallengeCardRes challengeCardList = cardService.selectRangeChallengeCard(principal.getName(),
			challengeCardReq.getStartTime(), challengeCardReq.getEndTime());
		return ResponseEntity.ok(challengeCardList);
	}

	//챌린지 카드 조회
	@Operation(summary = "챌린지 카드 조회 API", description = "챌린지 카드 한 장을 조회한다.")
	@GetMapping("/challenge/{id}")
	public ResponseEntity<ChallengeCardRes.ChallengeCard> selectChallengeCard(
		@Parameter(name = "cardId", description = "챌린지카드 id", required = true, example = "1", in = ParameterIn.PATH)
		@PathVariable Long id, Principal principal) {
		ChallengeCardRes.ChallengeCard challengeCard = cardService.selectChallengeCard(principal.getName(), id);
		return ResponseEntity.ok(challengeCard);
	}

}
