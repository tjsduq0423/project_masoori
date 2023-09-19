package com.fintech.masoori.global.redis;

import org.springframework.stereotype.Service;

@Service
public interface RedisService {
	// 이메일 인증코드 저장 및 업데이트
	void setEmailCode(String email, String code);

	// 이메일 인증코드 불러오기
	String getEmailCode(String email);

	// 이메일 인증코드 삭제
	void deleteEmailCode(String email);

	// 휴대폰 인증코드 저장 및 업데이트
	void setSmsCode(String phoneNumber, String code);

	// 휴대폰 인증코드 불러오기
	String getSmsCode(String phoneNumber);

	// 휴대폰 인증코드 삭제
	void deleteSmsCode(String phoneNumber);

	//행운의 색 저장
	void setUserColor(String email, String color, int limitTime);

	//행운의 색 조회
	String getUserColor(String email);

	//행운의 색 삭제
	void deleteUserColor(String email);
}
