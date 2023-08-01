package com.fc5.adminback.domain.annual.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    String getMessage();
    HttpStatus getHttpStatus();
}
