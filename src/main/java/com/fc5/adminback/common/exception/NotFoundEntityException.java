package com.fc5.adminback.common.exception;

import com.fc5.adminback.common.exception.errorcode.ErrorCode;

public class NotFoundEntityException extends CustomBusinessException {
    public NotFoundEntityException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
