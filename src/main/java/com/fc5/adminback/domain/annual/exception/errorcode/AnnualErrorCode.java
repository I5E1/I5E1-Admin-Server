package com.fc5.adminback.domain.annual.exception.errorcode;

import com.fc5.adminback.common.exception.errorcode.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AnnualErrorCode implements ErrorCode {
    NOT_FOUND_ANNUAL(HttpStatus.BAD_REQUEST, "존재하지 않는 연차입니다."),
    OVERLAPPING_PERIOD(HttpStatus.BAD_REQUEST, "이미 존재하는 연차와 기간이 겹칩니다."),
    NOT_ENOUGH_ANNUAL_COUNT(HttpStatus.BAD_REQUEST, "남은 연차 일 수가 부족합니다."),
    CAN_NOT_CHANGE_DATE_WITH_CANCELED(HttpStatus.BAD_REQUEST, "취소 상태로 변경 시 기존 날짜를 수정할 수 없습니다"),
    INVALID_UPDATE_STATUS(HttpStatus.BAD_REQUEST, "요청 및 승인이 된 연차를 제외한 나머지 연차는 수정이 불가능 합니다. 관리자에게 문의해주세요"),
    NOT_ENOUGH_ANNUAL_PAGE(HttpStatus.BAD_REQUEST, "더 이상 연차 정보가 존재하지 않습니다."),
    ;


    private final HttpStatus httpStatus;
    private final String message;

    AnnualErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
