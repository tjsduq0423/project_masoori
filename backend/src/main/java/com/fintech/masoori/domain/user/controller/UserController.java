package com.fintech.masoori.domain.user.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.nurigo.java_sdk.exceptions.CoolsmsException;

import com.fintech.masoori.domain.user.dto.EmailCheckReq;
import com.fintech.masoori.domain.user.dto.SendEmailReq;
import com.fintech.masoori.domain.user.dto.SendSmsReq;
import com.fintech.masoori.domain.user.exception.EmailDuplicationException;
import com.fintech.masoori.domain.user.exception.InvalidAuthCodeException;
import com.fintech.masoori.domain.user.exception.UserNotFoundException;
import com.fintech.masoori.domain.user.service.EmailService;
import com.fintech.masoori.domain.user.service.SmsService;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.service.UserService;
import com.fintech.masoori.global.error.ErrorCode;
import com.fintech.masoori.global.error.ErrorResponse;
import com.fintech.masoori.global.error.exception.InvalidValueException;
import com.fintech.masoori.global.redis.RedisService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/user")
@Tag(name = "user", description = "회원 API")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final EmailService emailService;
	private final RedisService redisService;
	private final SmsService smsService;

	@Operation(summary = "회원가입 시 이메일 인증코드 발송 API")
	@PostMapping("/email/signup")
	public ResponseEntity<?> sendSignupEmailCode(
		@Parameter(description = "회원 이메일", required = true)
		@RequestBody @Validated SendEmailReq sendEmailReq, BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors())
			throw new InvalidValueException("Invalid Input Value");
		String email = sendEmailReq.getEmail();
		Optional<User> findUser = userService.findByEmail(email);
		// 사용자 존재 => 중복 => 가입 안됨
		if (findUser.isPresent())
			throw new EmailDuplicationException("Email is Duplicated");
		return sendEmail(email);
	}

	@Operation(summary = "비밀번호 재설정 시 이메일 인증코드 발송 API")
	@PostMapping("/email/password")
	public ResponseEntity<?> sendPasswordEmailCode(
		@Parameter(description = "회원 이메일", required = true)
		@RequestBody @Validated SendEmailReq sendEmailReq, BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors())
			throw new InvalidValueException("Invalid Input Value");
		String email = sendEmailReq.getEmail();
		Optional<User> findUser = userService.findByEmail(email);
		// 사용자 존재x => 없는 사용자
		if (!findUser.isPresent())
			throw new UserNotFoundException("User Not Found");
		return sendEmail(email);
	}

	public ResponseEntity<?> sendEmail(String email) throws Exception {
		// 인증코드 redis 서버에 저장
		String code = emailService.createCode(8);
		redisService.setEmailCode(email, code);
		emailService.sendEmail(email, code);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "이메일 인증코드 검증 API")
	@PostMapping("/email/check")
	public ResponseEntity<?> verifyEmailCode(
		@Parameter(description = "회원 이메일, 인증 코드", required = true)
		@RequestBody EmailCheckReq emailCheckReq) {
		String inputCode = emailCheckReq.getCode();
		String savedCode = redisService.getEmailCode(emailCheckReq.getEmail());
		if (!inputCode.equals(savedCode))
			throw new InvalidAuthCodeException("Invalid Auth Code");
		return ResponseEntity.ok().build();
	}

	// @Operation(summary = "휴대폰 인증코드 발송 API")
	// @P
	@PostMapping("/sms")
	public ResponseEntity<?> sendOne(@RequestBody SendSmsReq sendSmsReq) {
		String userPhoneNumber = sendSmsReq.getPhoneNumber();
		String userName = sendSmsReq.getName();

		try {
			smsService.sendSms(userPhoneNumber, "12345");
		} catch (CoolsmsException e) {
			e.printStackTrace();
		}
		return null;
	}

}
