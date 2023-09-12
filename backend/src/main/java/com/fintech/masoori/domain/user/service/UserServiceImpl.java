package com.fintech.masoori.domain.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.user.dto.LoginReq;
import com.fintech.masoori.domain.user.dto.SignUpReq;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

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

	@Override
	public Optional<User> findByEmail(String email) {
		Optional<User> findUser = userRepository.findByEmail(email);
		return findUser;
	}

}
