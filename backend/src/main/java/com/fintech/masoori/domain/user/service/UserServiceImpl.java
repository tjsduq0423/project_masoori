package com.fintech.masoori.domain.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nurigo.java_sdk.exceptions.CoolsmsException;

import com.fintech.masoori.domain.user.dto.EmailCheckReq;
import com.fintech.masoori.domain.user.dto.LoginReq;
import com.fintech.masoori.domain.user.dto.SendSmsReq;
import com.fintech.masoori.domain.user.dto.SignUpReq;
import com.fintech.masoori.domain.user.dto.SmsCheckReq;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.exception.EmailDuplicationException;
import com.fintech.masoori.domain.user.exception.EmailMessagingException;
import com.fintech.masoori.domain.user.exception.InvalidAuthCodeException;
import com.fintech.masoori.domain.user.exception.SmsMessagingException;
import com.fintech.masoori.domain.user.exception.UserNotFoundException;
import com.fintech.masoori.domain.user.repository.UserRepository;
import com.fintech.masoori.global.config.jwt.TokenInfo;
import com.fintech.masoori.global.redis.RedisService;

import jakarta.mail.MessagingException;
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
	private final RedisService redisService;
	private final EmailService emailService;
	private final SmsService smsService;

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
	public TokenInfo login(LoginReq loginReq) {
		TokenInfo tokenInfo = null;
		return tokenInfo;
	}

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {

	}

	@Override
	public Optional<User> findByEmail(String email) {
		Optional<User> findUser = userRepository.findByEmail(email);
		return findUser;
	}

	@Override
	public void sendSignupEmailCode(String email) {
		Optional<User> findUser = findByEmail(email);
		// 사용자 존재 => 중복 => 가입 안됨
		if (findUser.isPresent())
			throw new EmailDuplicationException("Email is Duplicated");
		sendEmail(email);
	}

	@Override
	public void sendPasswordEmailCode(String email) {
		Optional<User> findUser = findByEmail(email);
		// 사용자 존재x => 없는 사용자
		if (!findUser.isPresent())
			throw new UserNotFoundException("User Not Found");
		sendEmail(email);
	}

	public void sendEmail(String email) {
		// 인증코드 redis 서버에 저장
		String code = emailService.createCode(8);
		redisService.setEmailCode(email, code);
		try {
			emailService.sendEmail(email, code);
		} catch (MessagingException e) {
			redisService.deleteEmailCode(email);
			throw new EmailMessagingException("Failed To Send Email");
		}
	}

	@Override
	public void verifyEmailCode(EmailCheckReq emailCheckReq) {
		String inputCode = emailCheckReq.getCode();
		String savedCode = redisService.getEmailCode(emailCheckReq.getEmail());
		if (!inputCode.equals(savedCode))
			throw new InvalidAuthCodeException("Invalid Auth Code");
	}

	@Override
	public void verifySmsCode(SmsCheckReq smsCheckReq) {
		String inputCode = smsCheckReq.getCode();
		String savedCode = redisService.getEmailCode(smsCheckReq.getPhoneNumber());
		if (!inputCode.equals(savedCode))
			throw new InvalidAuthCodeException("Invalid Auth Code");
	}

	@Override
	public void updateInfoAndSendSms(SendSmsReq sendSmsReq, User loginUser) {
		// 사용자 정보 업데이트
		String name = sendSmsReq.getName();
		String phoneNumber = sendSmsReq.getPhoneNumber();
		userRepository.updateInfo(loginUser.getEmail(), name, phoneNumber);
		// 인증코드 redis 서버에 저장 후 메시지 발송
		String code = smsService.createCode(6);
		redisService.setSmsCode(phoneNumber, code);
		try {
			smsService.sendSms(phoneNumber, code);
		} catch (CoolsmsException e) {
			redisService.deleteSmsCode(phoneNumber);
			throw new SmsMessagingException("Failed To Send Email");
		}
	}

}
