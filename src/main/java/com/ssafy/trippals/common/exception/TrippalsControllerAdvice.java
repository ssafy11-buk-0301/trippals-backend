package com.ssafy.trippals.common.exception;

import com.ssafy.trippals.common.exception.dto.ErrorCode;
import com.ssafy.trippals.common.exception.dto.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TrippalsControllerAdvice {
    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult handleLoginException(LoginException ex) {
        return new ErrorResult(ErrorCode.LOGIN_FAIL, ex.getMessage());
    }
}
