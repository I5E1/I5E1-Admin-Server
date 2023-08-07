package com.fc5.adminback.common.exception.errorcode;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    String getMessage();

    HttpStatus getHttpStatus();
}
