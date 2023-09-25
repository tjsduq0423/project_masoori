package com.fintech.masoori.domain.user.exception;

import com.fintech.masoori.global.error.ErrorCode;
import com.fintech.masoori.global.error.exception.BusinessException;

public class PasswordIsNotMatchedException extends BusinessException {
	public PasswordIsNotMatchedException(String message) {
		super(message, ErrorCode.PASSWORD_IS_NOT_MATCHED);
	}
}
