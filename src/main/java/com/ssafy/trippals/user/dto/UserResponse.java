package com.ssafy.trippals.user.dto;

import lombok.Data;

@Data
public class UserResponse {
    private Integer seq;

    private String name;
    private String profileImageUri;
    private String email;
}
