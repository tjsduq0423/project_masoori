package com.fintech.masoori.domain.user.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.masoori.domain.user.dto.CheckEmailDupulicatedReq;
import com.fintech.masoori.domain.user.dto.CheckEmailDupulicatedRes;
import com.fintech.masoori.domain.user.dto.EmailCheckReq;
import com.fintech.masoori.domain.user.dto.InfoRes;
import com.fintech.masoori.domain.user.dto.LoginReq;
import com.fintech.masoori.domain.user.dto.LoginRes;
import com.fintech.masoori.domain.user.dto.MonthlySpendingGoalReq;
import com.fintech.masoori.domain.user.dto.SendEmailReq;
import com.fintech.masoori.domain.user.dto.SendSmsReq;
import com.fintech.masoori.domain.user.dto.SignUpReq;
import com.fintech.masoori.domain.user.dto.SmsCheckReq;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.exception.UserNotFoundException;
import com.fintech.masoori.domain.user.service.UserService;
import com.fintech.masoori.global.error.exception.InvalidValueException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/user")
@Tag(name = "user", description = "회원 API")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@Operation(summary = "회원가입 시 이메일 인증코드 발송 API", description = "사용자 이메일을 중복체크하고 해당 이메일로 인증코드를 발송한다.")
	@PostMapping("/email/signup")
	public ResponseEntity<?> sendSignupEmailCode(
		@Parameter(description = "회원 이메일", required = true) @RequestBody @Validated SendEmailReq sendEmailReq,
		BindingResult bindingResult) {
		validateRequest(bindingResult);
		userService.sendSignupEmailCode(sendEmailReq.getEmail());
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "비밀번호 재설정 시 이메일 인증코드 발송 API", description = "가입된 사용자 이메일이면 해당 이메일로 인증코드를 발송한다.")
	@PostMapping("/email/password")
	public ResponseEntity<?> sendPasswordEmailCode(
		@Parameter(description = "회원 이메일", required = true) @RequestBody @Validated SendEmailReq sendEmailReq,
		BindingResult bindingResult) {
		validateRequest(bindingResult);
		userService.sendPasswordEmailCode(sendEmailReq.getEmail());
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "이메일 인증코드 검증 API", description = "사용자가 입력한 인증코드와 서버에 저장된 인증코드가 일치하는 지 검증한다.")
	@PostMapping("/email/check")
	public ResponseEntity<?> verifyEmailCode(
		@Parameter(description = "회원 이메일, 인증 코드", required = true) @RequestBody @Validated EmailCheckReq emailCheckReq,
		BindingResult bindingResult) {
		validateRequest(bindingResult);
		userService.verifyEmailCode(emailCheckReq);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "회원가입 API", description = "입력된 이메일, 패스워드로 DB에 저장한다.")
	@PostMapping("/signup")
	public ResponseEntity<?> signup(
		@Parameter(description = "이메일, 패스워드", required = true) @RequestBody @Validated SignUpReq signUpReq,
		BindingResult bindingResult) {
		validateRequest(bindingResult);
		userService.signUp(signUpReq);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "로그인 API", description = "입력된 이메일과 패스워드가 일치하는 지 검증한 뒤 로그인 토큰을 전달한다.")
	@PostMapping("/login")
	public ResponseEntity<?> login(
		@Parameter(description = "이메일, 패스워드", required = true) @RequestBody @Validated LoginReq loginReq,
		BindingResult bindingResult) {
		validateRequest(bindingResult);
		LoginRes loginRes = userService.login(loginReq);
		return ResponseEntity.ok(loginRes);
	}

	@Operation(summary = "로그아웃 API", description = "사용자를 로그아웃시키고 메인 페이지로 리다이렉트시킨다.")
	@PostMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, Principal principal) {
		userService.logout(request, response, principal.getName());
		return "redirect:https://masoori.site/";
	}

	@Operation(summary = "휴대폰 인증코드 발송 API", description = "입력된 사용자 정보를 업데이트하고 해당 휴대폰번호로 인증코드를 발송한다.")
	@PostMapping("/sms")
	public ResponseEntity<?> sendSms(
		@Parameter(description = "회원 전화번호", required = true) @RequestBody @Validated SendSmsReq sendSmsReq,
		BindingResult bindingResult, Principal principal) {
		validateRequest(bindingResult);
		User loginUser = loginUser(principal);
		userService.updateInfoAndSendSms(sendSmsReq, loginUser);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "휴대폰 인증코드 검증 API", description = "사용자가 입력한 인증코드와 서버에 저장된 인증코드가 일치하는 지 확인한다.")
	@PostMapping("/sms/check")
	public ResponseEntity<?> verifySmsCode(
		@Parameter(description = "회원 전화번호, 인증 코드", required = true) @RequestBody @Validated SmsCheckReq smsCheckReq,
		BindingResult bindingResult, Principal principal) {
		validateRequest(bindingResult);
		User loginUser = loginUser(principal);
		userService.verifySmsCode(smsCheckReq);
		userService.updateAuthentication(loginUser);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "마이페이지 유저 정보 조회 API", description = "마이페이지에서 필요한 사용자 정보를 조회한다.")
	@GetMapping("/info")
	public ResponseEntity<InfoRes> userInfo(Principal principal) {
		InfoRes infoRes = userService.getUserInfo(principal.getName());
		return ResponseEntity.ok().body(infoRes);
	}

	@Operation(summary = "유령을 통한 SMS, 소비카드 생성 연동 API", description = "유령이 나타나서 동의했을 때, SMS 알림과 소비카드 생성을 연동한다.")
	@PostMapping("/ghost")
	public ResponseEntity<?> updateIntegration(Principal principal) {
		User loginUser = loginUser(principal);
		userService.updateIntegration(loginUser);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "마이페이지 sms 알림 연동 변경 API", description = "마이페이지에서 sms 알림 연동 설정을 변경했을 때 토글로 작동한다.")
	@PostMapping("/alram")
	public ResponseEntity<?> updateSmsAlarm(Principal principal) {
		User loginUser = loginUser(principal);
		userService.updateSmsAlarm(loginUser);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "마이페이지 소비카드 생성 연동 변경 API", description = "마이페이지에서 소비카드 생성 연동 설정을 변경했을 때 토글로 작동한다.")
	@PostMapping("/generation")
	public ResponseEntity<?> updateCardGeneration(Principal principal) {
		User loginUser = loginUser(principal);
		userService.updateCardGeneration(loginUser);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "사용자 이메일 중복 체크 API", description = "사용자 이메일 입력시 중복 여부 확인을 True false로 알린다.")
	@PostMapping("/email-duplication/check")
	public ResponseEntity<CheckEmailDupulicatedRes> checkEmailDupulicated(
		@Parameter(description = "입력 이메일", required = true) @RequestBody CheckEmailDupulicatedReq checkEmailDupulicatedReq) {
		CheckEmailDupulicatedRes checkEmailDupulicatedRes = new CheckEmailDupulicatedRes(
			userService.checkEmail(checkEmailDupulicatedReq.getEmail()));
		return ResponseEntity.ok(checkEmailDupulicatedRes);
	}

	@Operation(summary = "사용자 월별 소비 목표 금액 설정 API", description = "사용자가 입력한 소비 금액을 사용자 정보에 업데이트한다.")
	@PostMapping("/monthly-spending")
	public ResponseEntity<?> updateMonthlySpending(
		@Parameter(description = "소비 목표 금액", required = true) @RequestBody MonthlySpendingGoalReq monthlySpendingGoalReq, Principal principal) {
		User loginUser = loginUser(principal);
		userService.updateMonthlySpendingGoal(loginUser, monthlySpendingGoalReq.getMonthlySpendingGoal());
		return ResponseEntity.ok().build();
	}

	private void validateRequest(BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new InvalidValueException("Invalid Input Value");
	}

	// 현재 로그인한 유저 정보를 찾아온다.
	public User loginUser(Principal principal) {
		return userService.findByEmail(principal.getName())
		                  .orElseThrow(() -> new UserNotFoundException("User is Not Found"));
	}



}
