package com.fintech.masoori.domain.user.exception;

import com.fintech.masoori.global.error.ErrorCode;
import com.fintech.masoori.global.error.exception.BusinessException;

public class SmsMessagingException extends BusinessException {
    public SmsMessagingException(String message) {
        super(message, ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
