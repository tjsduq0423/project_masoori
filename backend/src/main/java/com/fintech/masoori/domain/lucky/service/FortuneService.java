package com.fintech.masoori.domain.lucky.service;

import com.fintech.masoori.domain.lucky.dto.FortuneListRes;
import com.fintech.masoori.domain.lucky.dto.FortuneRes;

public interface FortuneService {

	FortuneListRes selectAllFortune();

	FortuneRes selectOneFortune(String email);

}
