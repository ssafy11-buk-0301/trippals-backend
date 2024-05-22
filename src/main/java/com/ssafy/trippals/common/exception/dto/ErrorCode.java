package com.ssafy.trippals.common.exception.dto;

import lombok.Getter;

@Getter
public enum ErrorCode {
    LOGIN_FAIL(101), UNAUTHORIZED(102), USER_NOT_FOUND(103), USER_ALREADY_EXIST(104),
    ROUTE_LIMIT_EXCEEDED(201),
    FILE_UPLOAD_FAIL(501),
    RECORD_ALREADY_EXIST(601);

    private final int code;

    ErrorCode(int code) { this.code = code; }
}
