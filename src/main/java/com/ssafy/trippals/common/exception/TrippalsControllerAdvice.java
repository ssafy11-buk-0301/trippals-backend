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

    @ExceptionHandler(UserAuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResult handleUserAuthException(UserAuthException ex) {
        return new ErrorResult(ErrorCode.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult handleUserNotFoundException(UserNotFoundException ex) {
        return new ErrorResult(ErrorCode.USER_NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return new ErrorResult(ErrorCode.USER_ALREADY_EXIST, ex.getMessage());
    }
}
