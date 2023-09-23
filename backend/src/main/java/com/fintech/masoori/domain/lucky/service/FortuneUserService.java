package com.fintech.masoori.domain.lucky.service;

import com.fintech.masoori.domain.lucky.dto.UserFortuneRes;

public interface FortuneUserService {
	UserFortuneRes selectAllUserFortune(String email);
}
