package com.fintech.masoori.domain.lucky.service;

import com.fintech.masoori.domain.lucky.dto.FortuneListRes;

public interface FortuneUserService {
	FortuneListRes selectAllUserFortune(String email);
}
