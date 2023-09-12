package com.fintech.masoori.domain.user.service;

import com.fintech.masoori.domain.user.dto.LoginReq;
import com.fintech.masoori.domain.user.dto.SignUpReq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
	boolean checkEmail(String email) throws Exception;

	boolean checkOAuthAccount(String email);

	void signUp(SignUpReq signUpReq);

	void login(LoginReq loginReq);

	void logout(HttpServletRequest request, HttpServletResponse response);
}
