package com.ssafy.trippals.common.exception;

public class LoginException extends RuntimeException {
    public static final String message = "로그인에 실패했습니다.";

    public LoginException() {
        super(message);
    }
}
