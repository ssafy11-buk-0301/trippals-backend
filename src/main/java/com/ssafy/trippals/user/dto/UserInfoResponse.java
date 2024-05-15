package com.ssafy.trippals.user.dto;

import lombok.Data;

@Data
public class UserInfoResponse {
    private Integer seq;

    private String name;
    private String profileImageUri;
    private String email;
}
