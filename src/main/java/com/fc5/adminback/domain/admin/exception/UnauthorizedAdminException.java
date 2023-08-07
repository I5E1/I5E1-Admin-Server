package com.fc5.adminback.domain.admin.exception;

import com.fc5.adminback.common.exception.errorcode.ErrorCode;
import com.fc5.adminback.common.exception.CustomBusinessException;

public class UnauthorizedAdminException extends CustomBusinessException {
    public UnauthorizedAdminException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
