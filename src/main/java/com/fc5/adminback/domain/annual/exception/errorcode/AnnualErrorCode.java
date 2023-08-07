package com.fc5.adminback.domain.annual.exception.errorcode;

import com.fc5.adminback.common.exception.errorcode.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AnnualErrorCode implements ErrorCode {
    NOT_FOUND_ANNUAL(HttpStatus.BAD_REQUEST, "존재하지 않는 연차입니다."),
    ;


    private final HttpStatus httpStatus;
    private final String message;

    AnnualErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
