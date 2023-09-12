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
}
