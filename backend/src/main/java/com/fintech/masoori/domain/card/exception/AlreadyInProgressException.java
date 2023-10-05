package com.fintech.masoori.domain.card.exception;

import com.fintech.masoori.global.error.ErrorCode;
import com.fintech.masoori.global.error.exception.BusinessException;

public class AlreadyInProgressException extends BusinessException {
	public AlreadyInProgressException(String message){super(message, ErrorCode.ALREADY_IN_PROGRESS);}
}
