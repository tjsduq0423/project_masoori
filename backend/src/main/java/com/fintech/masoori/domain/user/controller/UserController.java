package com.fintech.masoori.domain.user.controller;

import com.fintech.masoori.domain.user.dto.SendEmailReq;
import com.fintech.masoori.global.util.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.fintech.masoori.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@Slf4j
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
	public final UserService userService;
	public final EmailService emailService;

	@GetMapping
	public String Controller(Authentication authentication) {
		return "test";
	}

	@PostMapping("/signup/email")
	public ResponseEntity<?> sendSignupEmailCode(@RequestBody @Validated SendEmailReq sendEmailReq, BindingResult bindingResult) throws Exception{
		String email = sendEmailReq.getEmail();
		String code = emailService.createCode(8);
		emailService.sendEmail(email, code);
		return null;
	}
}
