package com.fintech.masoori.domain.user.service;

import java.util.HashMap;

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

	public void sendSms(String phoneNumber, String code) throws CoolsmsException {
		Message message = new Message(apiKey, apiSecretKey);

		HashMap<String, String> params = new HashMap<>();
		params.put("to", phoneNumber);
		params.put("from", "01051548989");
		params.put("type", "SMS");
		params.put("text", "인증번호 : " + code);
		params.put("app_version", "test app 1.2");

		message.send(params);
	}

}
