package com.ssafy.trippals.user.dto;

import lombok.Data;

@Data
public class UserInsert {
    private Integer seq;
    private String name;
    private String email;
    private String password;

    public UserInsert(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
