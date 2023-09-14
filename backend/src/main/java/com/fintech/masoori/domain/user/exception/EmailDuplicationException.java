package com.fintech.masoori.domain.user.exception;

import com.fintech.masoori.global.error.ErrorCode;
import com.fintech.masoori.global.error.exception.BusinessException;

public class EmailDuplicationException extends BusinessException {
	public EmailDuplicationException(String message) {
		super(message, ErrorCode.EMAIL_DUPLICATION);
	}
}
