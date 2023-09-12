package com.fintech.masoori.domain.user.controller;

import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.masoori.domain.user.dto.SendEmailReq;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.service.UserService;
import com.fintech.masoori.global.error.ErrorCode;
import com.fintech.masoori.global.error.ErrorResponse;
import com.fintech.masoori.global.redis.RedisService;
import com.fintech.masoori.global.util.EmailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
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
	private final RedisTemplate redisTemplate;

	@Operation(summary = "회원가입 시 이메일 인증 API")
	@PostMapping("/signup/email")
	public ResponseEntity<?> sendSignupEmailCode(
		@Parameter(description = "회원 이메일", required = true, example = "ssafy123@gmail.com")
		@RequestBody @Validated SendEmailReq sendEmailReq) {
		String email = sendEmailReq.getEmail();
		Optional<User> findUser = userService.findByEmail(email);
		// 사용자 존재 => 중복 => 가입 안됨
		if (findUser.isPresent()) {
			ErrorResponse response = ErrorResponse.of(ErrorCode.EMAIL_DUPLICATION);
			return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
		}
		String code = emailService.createCode(8);
		// 인증번호 redis 서버에 저장
		redisService.setEmailCode(email, code);
		try {
			emailService.sendEmail(email, code);

		} catch (MessagingException e) {
			e.printStackTrace();
			redisService.deleteEmailCode(email);
		}
		return ResponseEntity.ok().build();
	}





}
