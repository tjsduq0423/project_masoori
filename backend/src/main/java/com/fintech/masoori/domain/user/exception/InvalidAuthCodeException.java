package com.fintech.masoori.domain.user.exception;

import com.fintech.masoori.global.error.ErrorCode;
import com.fintech.masoori.global.error.exception.BusinessException;

public class InvalidAuthCodeException extends BusinessException {
	public InvalidAuthCodeException(String message) {
		super(message, ErrorCode.INVALID_AUTH_CODE);
	}
}
