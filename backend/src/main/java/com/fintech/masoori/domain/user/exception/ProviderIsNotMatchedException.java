package com.fintech.masoori.domain.user.exception;

import com.fintech.masoori.global.error.ErrorCode;
import com.fintech.masoori.global.error.exception.BusinessException;

public class ProviderIsNotMatchedException extends BusinessException {
	public ProviderIsNotMatchedException(String message) {
		super(message, ErrorCode.PROVIDER_IS_NOT_MATCHED);
	}
}
