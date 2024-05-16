package com.ssafy.trippals.common.exception;

public class UserAuthException extends RuntimeException {
    public final static String message = "권한이 없습니다.";

    public UserAuthException() {
        super(message);
    }
}
