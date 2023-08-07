package com.fc5.adminback.domain.admin.exception.errorcode;

import com.fc5.adminback.common.exception.errorcode.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AdminErrorCode implements ErrorCode {

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증되지 않은 관리자입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    AdminErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
