package com.fc5.adminback.common.exception;

import com.fc5.adminback.common.exception.errorcode.ErrorCode;

public class InvalidPageException extends CustomBusinessException {
    public InvalidPageException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
