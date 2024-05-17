package com.ssafy.trippals.common.exception;

public class RouteLimitExceededException extends RuntimeException {
    public static final String MESSAGE_FORMAT = "최대 여행 경로 수는 %d 입니다.";

    public RouteLimitExceededException(int max_route) {
        super(String.format(MESSAGE_FORMAT, max_route));
    }
}
