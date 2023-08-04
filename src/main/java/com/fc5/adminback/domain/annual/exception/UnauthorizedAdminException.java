package com.fc5.adminback.domain.annual.exception;

public class UnauthorizedAdminException extends CustomBusinessException {
    public UnauthorizedAdminException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
