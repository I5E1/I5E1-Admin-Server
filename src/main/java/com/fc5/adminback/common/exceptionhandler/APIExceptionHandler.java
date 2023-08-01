package com.fc5.adminback.common.exceptionhandler;

import com.fc5.adminback.common.APIErrorResponse;
import com.fc5.adminback.common.util.ErrorMessageConverter;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class APIExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<?> typeMismatchExceptionHandle(TypeMismatchException e, BindingResult bindingResult) {
        return handleExceptionInternal("잘못된 형식입니다", HttpStatus.BAD_REQUEST, bindingResult);
    }

    @ExceptionHandler
    public ResponseEntity<?> httpMessageNotReadableExceptionHandle(HttpMessageNotReadableException e, BindingResult bindingResult) {
        return handleExceptionInternal(e.getMessage(), HttpStatus.BAD_REQUEST, bindingResult);
    }
    @ExceptionHandler
    public ResponseEntity<?> constraintViolationExceptionHandle(ConstraintViolationException e, BindingResult bindingResult) {
        return handleExceptionInternal(e.getMessage(), HttpStatus.BAD_REQUEST, bindingResult);
    }

    @ExceptionHandler
    public ResponseEntity<?> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException e, BindingResult bindingResult) {
        return handleExceptionInternal(e.getMessage(), HttpStatus.BAD_REQUEST, bindingResult);
    }



    @ExceptionHandler
    public ResponseEntity<?> bindExceptionHandle(BindException e, BindingResult bindingResult) {
        return handleExceptionInternal("잘못된 형식입니다", HttpStatus.BAD_REQUEST, bindingResult);
    }

    @ExceptionHandler
    public ResponseEntity<?> runtimeExceptionHandle(RuntimeException e, BindingResult bindingResult) {
        System.out.println("hello!@");
        return handleExceptionInternal(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, bindingResult);
    }
    @ExceptionHandler
    public ResponseEntity<?> exceptionHandle(Exception e, BindingResult bindingResult) {
        return handleExceptionInternal(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, bindingResult);
    }

    public ResponseEntity<?> handleExceptionInternal(String errorMessage, HttpStatus httpStatus, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return APIErrorResponse.of(httpStatus, ErrorMessageConverter.convert(bindingResult));
        }
        return APIErrorResponse.of(httpStatus, new String[]{errorMessage});
    }
}
