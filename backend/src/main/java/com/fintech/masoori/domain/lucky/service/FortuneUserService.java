package com.fintech.masoori.domain.lucky.service;

import com.fintech.masoori.domain.lucky.dto.FortuneRes;

public interface FortuneUserService {
	FortuneRes selectAllUserFortune(String email);
}
