package com.ssafy.trippals.common.exception.dto;

import lombok.Getter;

@Getter
public enum ErrorCode {
    LOGIN_FAIL(101), UNAUTHORIZED(102), USER_NOT_FOUND(103);

    private final int code;

    ErrorCode(int code) { this.code = code; }
}
