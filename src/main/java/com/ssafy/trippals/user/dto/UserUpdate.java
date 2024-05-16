package com.ssafy.trippals.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserUpdate {
    private int seq;
    private String name;
    private String profileImage;

    public UserUpdate(UserInfo userInfo) {
        this.seq = userInfo.getSeq();
        this.name = userInfo.getName();
        this.profileImage = userInfo.getProfileImageUri();
    }
}
