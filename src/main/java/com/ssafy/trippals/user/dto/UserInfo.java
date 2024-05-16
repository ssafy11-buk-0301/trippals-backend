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

    public UserInfo(String name, String profileImageUri, String email) {
        this.name = name;
        this.profileImageUri = profileImageUri;
        this.email = email;
    }
}
