package com.fc5.adminback.domain.annual.exception;

import com.fc5.adminback.common.util.ErrorCode;

public class UnauthorizedAdminException extends CustomBusinessException {
    public UnauthorizedAdminException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
