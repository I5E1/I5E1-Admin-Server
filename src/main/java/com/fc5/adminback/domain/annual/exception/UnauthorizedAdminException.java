package com.fc5.adminback.domain.annual.exception;

import com.fc5.adminback.domain.annual.exception.AnnualErrorCode;
import lombok.Getter;

public class UnauthorizedAdminException extends CustomBusinessException {
    public UnauthorizedAdminException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
