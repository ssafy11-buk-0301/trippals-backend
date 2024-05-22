package com.ssafy.trippals.common.exception;

public class DupInsertException extends RuntimeException{
    public static String MESSAGE = "이미 존재하는 값 입력 불가";

    public DupInsertException() {
        super(MESSAGE);
    }
}
