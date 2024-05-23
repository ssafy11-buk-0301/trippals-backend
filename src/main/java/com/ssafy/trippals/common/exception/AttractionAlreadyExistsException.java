package com.ssafy.trippals.common.exception;

public class AttractionAlreadyExistsException extends RuntimeException {
    public static final String MESSAGE = "여행지가 이미 존재합니다.";

    public AttractionAlreadyExistsException() {
        super(MESSAGE);
    }
}
