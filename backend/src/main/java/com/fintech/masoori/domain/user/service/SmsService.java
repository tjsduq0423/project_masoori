package com.fintech.masoori.domain.user.service;

import java.util.HashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
public class SmsService {

	@Value("${spring.sms.api}")
	private String apiKey;

	@Value("${spring.sms.api-secret}")
	private String apiSecretKey;

	@Value("${spring.sms.from}")
	private String from;

	public void sendSms(String phoneNumber, String code) throws CoolsmsException {
		Message message = new Message(apiKey, apiSecretKey);

		HashMap<String, String> params = new HashMap<>();
		params.put("to", phoneNumber);
		params.put("from", from);
		params.put("type", "SMS");
		params.put("text", "[마수리] 인증번호: " + code + "\n인증번호를 입력해 주세요.");
		params.put("app_version", "test app 1.2");

		message.send(params);
	}

	public String createCode(int codeLength) {
		Random random = new Random();
		StringBuilder key = new StringBuilder();

		for (int i = 0; i < codeLength; i++) {
			key.append(random.nextInt(10));
		}
		return key.toString();
	}

}
