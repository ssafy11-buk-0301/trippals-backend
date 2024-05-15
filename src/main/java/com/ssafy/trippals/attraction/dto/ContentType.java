package com.ssafy.trippals.attraction.dto;

public enum ContentType {
    ATTRACTION(12), CULTURAL_CENTER(14),
    FESTIVAL(15), COURSE(25),
    LEPORTS(28), ACCOMMODATION(32),
    SHOPPING(38), RESTAURANT(39);

    private final int code;

    public int getCode() {
        return code;
    }

    ContentType(int code) {
        this.code = code;
    }
}
