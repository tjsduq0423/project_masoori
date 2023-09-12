package com.fintech.masoori.domain.user.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fintech.masoori.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
	public final UserService userService;

	@GetMapping
	public String Controller(Authentication authentication) {
		return "test";
	}
}
