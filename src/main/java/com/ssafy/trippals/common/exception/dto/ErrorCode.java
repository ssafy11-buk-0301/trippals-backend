package com.ssafy.trippals.common.exception.dto;

import lombok.Getter;

@Getter
public enum ErrorCode {
    LOGIN_FAIL(101);

    private final int code;

    ErrorCode(int code) { this.code = code; }
}
