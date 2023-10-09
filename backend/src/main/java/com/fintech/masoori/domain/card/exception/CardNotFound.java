package com.fintech.masoori.domain.card.exception;

import com.fintech.masoori.global.error.ErrorCode;
import com.fintech.masoori.global.error.exception.BusinessException;

public class CardNotFound extends BusinessException {
	public CardNotFound(String message){super(message, ErrorCode.CARD_NOT_FOUND);}
}
