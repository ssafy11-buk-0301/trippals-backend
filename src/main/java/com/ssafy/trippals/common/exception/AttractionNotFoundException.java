package com.ssafy.trippals.common.exception;

public class AttractionNotFoundException extends RuntimeException {
    public static String MESSAGE = "여행지를 찾을 수 없습니다.";

    public AttractionNotFoundException() {
        super(MESSAGE);
    }
}
