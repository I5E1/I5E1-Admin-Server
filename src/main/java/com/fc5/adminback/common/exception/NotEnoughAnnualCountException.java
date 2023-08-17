package com.fc5.adminback.common.exception;

import com.fc5.adminback.common.exception.errorcode.ErrorCode;

public class NotEnoughAnnualCountException extends CustomBusinessException {

    public NotEnoughAnnualCountException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
