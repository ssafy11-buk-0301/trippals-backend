package com.ssafy.trippals.attraction.dto;

import java.util.Arrays;

public enum ContentType {
    ATTRACTION(12), CULTURAL_CENTER(14),
    FESTIVAL(15), COURSE(25),
    LEPORTS(28), ACCOMMODATION(32),
    SHOPPING(38), RESTAURANT(39), INVALID(99);

    private final int code;

    public int getCode() {
        return code;
    }

    public static ContentType getContentType(int code) {
        return Arrays.stream(ContentType.values())
                .filter(contentType -> contentType.getCode() == code)
                .findFirst()
                .orElse(ContentType.INVALID);
    }

    ContentType(int code) {
        this.code = code;
    }
}
