package com.fintech.masoori.domain.user.service;

import java.util.List;
import java.util.Optional;

import com.fintech.masoori.domain.user.dto.EmailCheckReq;
import com.fintech.masoori.domain.user.dto.InfoRes;
import com.fintech.masoori.domain.user.dto.LoginReq;
import com.fintech.masoori.domain.user.dto.LoginRes;
import com.fintech.masoori.domain.user.dto.SendSmsReq;
import com.fintech.masoori.domain.user.dto.SignUpReq;
import com.fintech.masoori.domain.user.dto.SmsCheckReq;
import com.fintech.masoori.domain.user.entity.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
	List<User> findUsersByIsAuthenticated(Boolean isAuthentication);

	boolean checkEmail(String email);

	boolean checkOAuthAccount(String email);

	void signUp(SignUpReq signUpReq);

	LoginRes login(LoginReq loginReq);

	InfoRes getUserInfo(String email);

	void logout(HttpServletRequest request, HttpServletResponse response, String email);

	// 사용자 이메일로 조회
	Optional<User> findByEmail(String email);

	User findById(Long id);

	void updateInfoAndSendSms(SendSmsReq sendSmsReq, User loginUser);

	void sendSignupEmailCode(String email);

	void sendPasswordEmailCode(String email);

	void verifyEmailCode(EmailCheckReq emailCheckReq);

	void verifySmsCode(SmsCheckReq smsCheckReq);

	void updateIntegration(User loginUser);

	void updateSmsAlarm(User loginUser);

	void updateCardGeneration(User loginUser);

	void updateMonthlySpendingGoal(User loginUser, Integer monthlySpendingGoal);
}
