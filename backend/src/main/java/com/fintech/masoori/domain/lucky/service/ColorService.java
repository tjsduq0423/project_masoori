package com.fintech.masoori.domain.lucky.service;

import com.fintech.masoori.domain.lucky.dto.ColorRes;

public interface ColorService {
	ColorRes selectOneColor(String email);
}
