package com.ssafy.trippals.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfo {
    private Integer seq;

    private String name;
    private String profileImageUri;
    private String email;
}
