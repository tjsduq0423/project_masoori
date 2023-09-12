package com.fintech.masoori.domain.user.service;

import java.util.Optional;

import com.fintech.masoori.domain.user.dto.LoginReq;
import com.fintech.masoori.domain.user.dto.SignUpReq;
import com.fintech.masoori.domain.user.entity.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
	boolean checkEmail(String email) throws Exception;

	boolean checkOAuthAccount(String email);

	void signUp(SignUpReq signUpReq);

	void login(LoginReq loginReq);

	void logout(HttpServletRequest request, HttpServletResponse response);

	// 사용자 이메일로 조회
	Optional<User> findByEmail(String email);
}
