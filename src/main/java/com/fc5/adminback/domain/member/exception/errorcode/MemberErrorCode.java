package com.fc5.adminback.domain.member.exception.errorcode;

import com.fc5.adminback.common.exception.errorcode.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum MemberErrorCode implements ErrorCode {
    INVALID_PAGE(HttpStatus.BAD_REQUEST, "더 이상 회원 정보가 존재하지 않습니다."),
    INVALID_INDEX(HttpStatus.BAD_REQUEST, "존재하지 않는 회원입니다"),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    MemberErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
