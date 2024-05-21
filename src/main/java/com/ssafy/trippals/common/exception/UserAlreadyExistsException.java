package com.ssafy.trippals.common.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public final static String message = "이미 존재하는 사용자입니다.";

    public UserAlreadyExistsException() {
        super(message);
    }
}
