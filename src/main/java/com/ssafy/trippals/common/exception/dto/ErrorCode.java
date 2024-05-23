package com.ssafy.trippals.common.exception.dto;

import lombok.Getter;

@Getter
public enum ErrorCode {
    LOGIN_FAIL(101), UNAUTHORIZED(102), USER_NOT_FOUND(103), USER_ALREADY_EXIST(104),
    ROUTE_NOT_FOUND(201),ROUTE_ALREADY_EXIST(202), ROUTE_LIMIT_EXCEEDED(203),
    ATTRACTION_NOT_FOUND(301), ATTRACTION_ALREADY_EXIST(302),
    FILE_UPLOAD_FAIL(501),
    RECORD_ALREADY_EXIST(601);

    private final int code;

    ErrorCode(int code) { this.code = code; }
}
