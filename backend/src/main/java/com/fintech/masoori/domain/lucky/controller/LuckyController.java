package com.fintech.masoori.domain.lucky.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.masoori.domain.lucky.dto.ColorRes;
import com.fintech.masoori.domain.lucky.dto.FortuneListRes;
import com.fintech.masoori.domain.lucky.dto.FortuneRes;
import com.fintech.masoori.domain.lucky.service.ColorService;
import com.fintech.masoori.domain.lucky.service.FortuneService;
import com.fintech.masoori.domain.lucky.service.FortuneUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/lucky")
@RequiredArgsConstructor
@Tag(name = "Lucky", description = "금전운 및 행운의 컬러 API")
public class LuckyController {
	private final FortuneService fortuneService;
	private final FortuneUserService fortuneUserService;
	private final ColorService colorService;

	//행운의 색 조회
	@Operation(summary = "행운의 색 조회 API", description = "하루에 한번 이용할 수 있는 행운의 색을 반환한다.")
	@GetMapping("/user/color")
	public ResponseEntity<ColorRes> selectLoginColor(Principal principal) {
		ColorRes color = colorService.selectOneColor(principal.getName());
		return ResponseEntity.ok(color);
	}

	@Operation(summary = "행운의 색 조회(비로그인) API", description = "비로그인 유저가 사용하는 행운의 색 조회 API")
	@GetMapping("/color")
	public ResponseEntity<ColorRes> selectColor() {
		ColorRes color = colorService.selectOneColor("");
		return ResponseEntity.ok(color);
	}

	//금전운 조회
	@Operation(summary = "금전운 조회 API", description = "하루에 한번 이용할 수 있는 금전운을 반환한다. 뽑은 금전운 카드는 사용자에게 등록된다.")
	@GetMapping("/user/fortune")
	public ResponseEntity<FortuneRes> selectLoginFortune(Principal principal) {
		FortuneRes fortune = fortuneService.selectOneFortune(principal.getName());
		return ResponseEntity.ok(fortune);
	}

	//비로그인 금전운 조회
	@Operation(summary = "금전운 조회(비로그인) API", description = "비로그인 유저가 사용하는 금전운 조회 API")
	@GetMapping("/fortune")
	public ResponseEntity<FortuneRes> selectFortune() {
		FortuneRes fortune = fortuneService.selectOneFortune("");
		return ResponseEntity.ok(fortune);
	}

	//금전운 전체 조회
	@Operation(summary = "금전운 전체 조회 API", description = "전체 금전운 카드(40장)을 리스트로 반환")
	@GetMapping("/fortune/list")
	public ResponseEntity<FortuneListRes> selectAllFortune() {
		FortuneListRes fortuneList = fortuneService.selectAllFortune();
		return ResponseEntity.ok(fortuneList);
	}

	//사용자 금전운 전체 조회
	@Operation(summary = "사용자 금전운 전체 조회 API", description = "사용자가 소유하고 있는 금전운 카드를 리스트로 반환")
	@GetMapping("/userfortune")
	public ResponseEntity<FortuneListRes> selectAllUserFortune(Principal principal) {
		FortuneListRes fortuneList = fortuneUserService.selectAllUserFortune(principal.getName());
		return ResponseEntity.ok(fortuneList);
	}

}
