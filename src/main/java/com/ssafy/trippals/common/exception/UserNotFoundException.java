package com.ssafy.trippals.common.exception;

public class UserNotFoundException extends RuntimeException {
    private static final String message = "사용자를 찾을 수 없습니다.";

    public UserNotFoundException() {
        super(message);
    }
}
