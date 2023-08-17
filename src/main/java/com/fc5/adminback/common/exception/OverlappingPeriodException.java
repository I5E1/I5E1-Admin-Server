package com.fc5.adminback.common.exception;

import com.fc5.adminback.common.exception.CustomBusinessException;
import com.fc5.adminback.common.exception.errorcode.ErrorCode;

public class OverlappingPeriodException extends CustomBusinessException {
    public OverlappingPeriodException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
