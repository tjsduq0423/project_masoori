package com.fintech.masoori.domain.user.service;

import org.springframework.stereotype.Service;

import com.fintech.masoori.domain.user.dto.LoginReq;
import com.fintech.masoori.domain.user.dto.SignUpReq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService {
	@Override
	public boolean checkEmail(String email) throws Exception {
		return false;
	}

	@Override
	public boolean checkOAuthAccount(String email) {
		return false;
	}

	@Override
	public void signUp(SignUpReq signUpReq) {

	}

	@Override
	public void login(LoginReq loginReq) {

	}

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {

	}
}
