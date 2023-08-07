package com.fc5.adminback.domain.duty.exception.errorcode;

import com.fc5.adminback.common.exception.errorcode.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum DutyErrorCode implements ErrorCode {

    CAN_NOT_COMPLETED(HttpStatus.BAD_REQUEST, "해당 시점은 완료할 수 없는 시점입니다."),
    NOT_ENOUGH_DUTY_PAGE(HttpStatus.BAD_REQUEST, "더 이상 당직 정보가 존재하지 않습니다."),
    NOT_FOUND_DUTY(HttpStatus.BAD_REQUEST, "존재하지 않는 당직 정보입니다"),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    DutyErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
    }
