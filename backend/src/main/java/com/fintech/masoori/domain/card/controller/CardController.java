package com.fintech.masoori.domain.card.controller;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.masoori.domain.card.dto.BasicCardRes;
import com.fintech.masoori.domain.card.dto.ChallengeCardRes;
import com.fintech.masoori.domain.card.dto.ProfileCardReq;
import com.fintech.masoori.domain.card.dto.UserCardListRes;
import com.fintech.masoori.domain.card.exception.CanCreateException;
import com.fintech.masoori.domain.card.exception.AlreadyInProgressException;
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
	public ResponseEntity<UserCardListRes> selectConsumeCard(
		@Parameter(description = "조회를 시작할 연, 월", required = true, example = "2023-09-16T07:42:34.76")
		@RequestParam("startDate") LocalDateTime startDate,
		@Parameter(description = "조회를 종료할 연, 월", required = true, example = "2023-09-26T07:42:34.76")
		@RequestParam("endDate") LocalDateTime endDate,
		Principal principal) {
		UserCardListRes basicCardList = cardService.selectRangeBasicCard(principal.getName(), startDate,
			endDate);
		return ResponseEntity.ok(basicCardList);
	}

	//소비 카드 조회
	@Operation(summary = "소비 카드 조회 API", description = "소비 카드 한 장을 조회한다.")
	@GetMapping("/consume/{id}")
	public ResponseEntity<BasicCardRes.BasicCard> detailConsumeCard(
		@Parameter(name = "id", description = "소비카드 id", required = true, example = "1", in = ParameterIn.PATH)
		@PathVariable Long id, Principal principal) {
		BasicCardRes.BasicCard basicCard = cardService.selectBasicCard(principal.getName(), id);
		return ResponseEntity.ok(basicCard);
	}

	//챌린지 카드 범위 조회
	@Operation(summary = "챌린지 카드 범위 조회 API", description = "유저의 챌린지 카드를 연, 월을 기준으로 조회")
	@GetMapping("/challenge")
	public ResponseEntity<UserCardListRes> selectChallenge(
		@Parameter(description = "조회를 시작할 연, 월", required = true, example = "2023-09-16T07:42:34.76")
		@RequestParam("startDate") LocalDateTime startDate,
		@Parameter(description = "조회를 종료할 연, 월", required = true, example = "2023-09-26T07:42:34.76")
		@RequestParam("endDate") LocalDateTime endDate, Principal principal) {
		UserCardListRes challengeCardList = cardService.selectRangeChallengeCard(principal.getName(),
			startDate, endDate);
		return ResponseEntity.ok(challengeCardList);
	}

	//챌린지 카드 조회
	@Operation(summary = "챌린지 카드 조회 API", description = "챌린지 카드 한 장을 조회한다.")
	@GetMapping("/challenge/{id}")
	public ResponseEntity<ChallengeCardRes.ChallengeCard> selectChallengeCard(
		@Parameter(name = "id", description = "챌린지카드 id", required = true, example = "1", in = ParameterIn.PATH)
		@PathVariable Long id, Principal principal) {
		ChallengeCardRes.ChallengeCard challengeCard = cardService.selectChallengeCard(principal.getName(), id);
		return ResponseEntity.ok(challengeCard);
	}

	//이번주 챌린지 카드 조회
	@Operation(summary = "해당 주차 사용자 소비카드 조회 API", description = "사용자가 이번주에 소비카드를 만들었는지, 만들지 않았는지 판단한다. 1.최초 사용자, 이번주에 만들어진 카드가 없는 사용자 : 무조건 카드가 없기 때문에 400('C008')에러 발생 2.이번주에 카드를 이미 만든 사용자 : 이번주에 만든 카드 정보가 그대로 반환 3.카드가 만들어지고 있는 사용자 : 400('C009')에러 발생")
	@GetMapping("/consume/recent")
	public ResponseEntity<BasicCardRes.BasicCard> selectUserLastBasicCard(@Parameter(name = "now", description = "현재 시간", example = "2023-09-26T07:42:34.76") LocalDateTime now, Principal principal) {
		BasicCardRes.BasicCard basicCard = cardService.selectUserRecentBasicCard(principal.getName(), now);
		//카드를 만들 수 있는 사용자
		if(basicCard == null){
			throw new CanCreateException("User can create Card");
		}
		//카드가 만들어 지고 있는 사용자
		if(basicCard.getCard().getId() != null && basicCard.getCard().getImagePath() == null){
			throw new AlreadyInProgressException("Creating Card is in progress");
		}
		//이미 카드가 존재하는 사용자
		return ResponseEntity.ok(basicCard);
	}

	@Operation(summary = "프로필 이미지 등록 API", description = "프로필에 등록할 이미지 ID")
	@PostMapping("/profileimage")
	public ResponseEntity<?> updateUserProfileCard(@RequestBody ProfileCardReq profileCardReq, Principal principal){
		cardService.updateUserProfileImage(principal.getName(), profileCardReq.getId());
		return ResponseEntity.ok().build();
	}

}
