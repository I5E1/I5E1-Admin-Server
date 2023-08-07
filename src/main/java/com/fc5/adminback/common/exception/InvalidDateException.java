package com.fc5.adminback.common.exception;

import com.fc5.adminback.common.exception.errorcode.ErrorCode;

public class InvalidDateException extends CustomBusinessException {

    public InvalidDateException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
