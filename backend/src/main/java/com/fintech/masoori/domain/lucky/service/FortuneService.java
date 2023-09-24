package com.fintech.masoori.domain.lucky.service;

import com.fintech.masoori.domain.lucky.dto.FortuneRes;

public interface FortuneService {

	FortuneRes selectAllFortune();

	FortuneRes.Fortune selectOneFortune(String email);
}
