package com.fintech.masoori.domain.lucky.controller;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.masoori.domain.lucky.dto.ColorRes;
import com.fintech.masoori.domain.lucky.dto.FortuneRes;
import com.fintech.masoori.domain.lucky.service.FortuneService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/lucky")
@RequiredArgsConstructor
@Tag(name = "Lucky", description = "금전운 및 행운의 컬러 API")
public class LuckyController {
	private final FortuneService fortuneService;

	//행운의 색 조회
	@Operation(summary = "행운의 색 조회 API", description = "하루에 한번 이용할 수 있는 행운의 색을 반환한다.")
	@GetMapping("/color/{now}")
	public ResponseEntity<ColorRes> selectColor(
		@Parameter(description = "현재 시간(Date객체)", required = true, example = "2023-09-14T15:30:45")
		@PathVariable LocalDateTime now, Principal principal) {
		return ResponseEntity.ok().build();
	}

	//금전운 조회
	@Operation(summary = "금전운 조회 API", description = "하루에 한번 이용할 수 있는 금전운을 반환한다. 뽑은 금전운 카드는 사용자에게 등록된다.")
	@GetMapping("/fortune/{now}")
	public ResponseEntity<FortuneRes.Fortune> selectFortune(
		@Parameter(description = "현재 시간(Date객체)", required = true, example = "2023-09-14T15:30:45")
		@PathVariable LocalDateTime now, Principal principal) {
		return ResponseEntity.ok().build();
	}

	//금전운 전체 조회
	@Operation(summary = "금전운 전체 조회 API", description = "전체 금전운 카드(40장)을 리스트로 반환")
	@GetMapping("/fortune")
	public ResponseEntity<FortuneRes> selectAllFortune() {
		return ResponseEntity.ok().build();
	}

	//사용자 금전운 전체 조회
	@Operation(summary = "사용자 금전운 전체 조회 API", description = "사용자가 소유하고 있는 금전운 카드를 리스트로 반환")
	@GetMapping("/userfortune")
	public ResponseEntity<FortuneRes> selectAllUserFortune(Principal principal) {
		return ResponseEntity.ok().build();
	}

}
