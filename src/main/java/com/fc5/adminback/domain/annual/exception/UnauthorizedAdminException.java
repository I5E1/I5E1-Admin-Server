package com.fc5.adminback.domain.annual.exception;

import com.fc5.adminback.domain.annual.exception.AnnualErrorCode;

public class UnauthorizedAdminException extends RuntimeException {

    private ErrorCode errorCode;

    public UnauthorizedAdminException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
