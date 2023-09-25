package com.fintech.masoori.domain.credit.exception;

import com.fintech.masoori.global.error.ErrorCode;
import com.fintech.masoori.global.error.exception.BusinessException;

public class InvalidIDException extends BusinessException {
	public InvalidIDException(String message) {
		super(message, ErrorCode.ENTITY_NOT_FOUND);
	}
}
