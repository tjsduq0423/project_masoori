package com.fintech.masoori.domain.card.exception;

import com.fintech.masoori.global.error.ErrorCode;
import com.fintech.masoori.global.error.exception.BusinessException;

public class CanCreateException extends BusinessException {
	public CanCreateException(String message){super(message, ErrorCode.CAN_CREATE);}
}
