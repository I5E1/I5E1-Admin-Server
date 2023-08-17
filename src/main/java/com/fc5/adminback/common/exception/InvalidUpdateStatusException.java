package com.fc5.adminback.common.exception;

import com.fc5.adminback.common.exception.errorcode.ErrorCode;

public class InvalidUpdateStatusException extends CustomBusinessException {
    public InvalidUpdateStatusException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
