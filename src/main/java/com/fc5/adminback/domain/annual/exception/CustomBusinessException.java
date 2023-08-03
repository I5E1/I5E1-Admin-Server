package com.fc5.adminback.domain.annual.exception;

import lombok.Getter;

@Getter
public class CustomBusinessException extends RuntimeException {

    private ErrorCode errorCode;

    public CustomBusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
