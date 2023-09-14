package com.fintech.masoori.domain.user.exception;

import com.fintech.masoori.global.error.ErrorCode;
import com.fintech.masoori.global.error.exception.BusinessException;

public class EmailMessagingException extends BusinessException {
    public EmailMessagingException(String message) {
        super(message, ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
