package com.fc5.adminback.common.util;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    String getMessage();

    HttpStatus getHttpStatus();
}
