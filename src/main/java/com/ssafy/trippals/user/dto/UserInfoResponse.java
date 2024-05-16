package com.ssafy.trippals.user.dto;

import lombok.Data;

@Data
public class UserInfoResponse {
    private Integer seq;

    private String name;
    private String profileImageUri;
    private String email;

    public UserInfoResponse(UserInfo userInfo) {
        this.seq = userInfo.getSeq();
        this.name = userInfo.getName();
        this.profileImageUri = userInfo.getProfileImageUri();
        this.email = userInfo.getEmail();
    }
}
