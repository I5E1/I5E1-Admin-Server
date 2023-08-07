package com.fc5.adminback.common.exceptionhandler;

import com.fc5.adminback.common.response.APIErrorResponse;
import com.fc5.adminback.common.util.ErrorMessageConverter;
import com.fc5.adminback.common.exception.CustomBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.stream.IntStream;

@RestControllerAdvice
@Slf4j
public class APIExceptionHandler {

    /**
     * java bean validation Error -> MethodArgumentNotValidException [o]
     * typeError -> BindException [o]
     * JSON이 올바르지 않거나 비어있거나, text 형태로 들어올 떄 -> HttpMessageNotReadableException [o]
     * PathVariable 타입 에러 -> MethodArgumentTypeMismatchException [o]
     * API 에러 -> NoHandlerFoundException [o]
     * URL은 있으나 HttpMethod가 올바르지 않음 -> HttpRequestMethodNotSupportedException [o]
     * 커스텀 익셉션
     */

    @ExceptionHandler
    public ResponseEntity<?> httpRequestMethodNotSupportedExceptionHandle(HttpRequestMethodNotSupportedException e) {
        return handleExceptionInternal(e, "지원하지 않는 HttpMethod 요청입니다.", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler
    public ResponseEntity<?> methodArgumentTypeMismatchExceptionHandle(MethodArgumentTypeMismatchException e) {
        return handleExceptionInternal(e, "PathVariable 타입이 올바르지 않습니다.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> noHandlerFoundExceptionHandle(NoHandlerFoundException e) {
        return handleExceptionInternal(e, "API가 존재하지 않습니다.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> httpMessageNotReadableExceptionHandle(HttpMessageNotReadableException e) {
        e.printStackTrace();
        return handleExceptionInternal(e, "RequestBody 형식이 올바르지 않습니다", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException e) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] convert = ErrorMessageConverter.convert(e.getBindingResult());
        IntStream.range(1, convert.length + 1)
                .forEach(idx -> {
                    stringBuilder.append(idx).append(". ").append(convert[idx - 1]);
                    if (idx != convert.length) {
                        stringBuilder.append(" | ");
                    }
                });

        return handleExceptionInternal(e, stringBuilder.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> bindExceptionHandle(BindException e) {
        return handleExceptionInternal(e, "요청 형식이 올바르지 않습니다", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> customBusinessExceptionHandle(CustomBusinessException e) {
        return handleExceptionInternal(e, e.getMessage(), e.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<?> runtimeExceptionHandle(RuntimeException e) {
        return handleExceptionInternal(e, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<?> exceptionHandle(Exception e) {
        return handleExceptionInternal(e, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<?> handleExceptionInternal(Exception e, String errorMessage, HttpStatus httpStatus) {
        return APIErrorResponse.of(httpStatus, errorMessage);
    }
}
