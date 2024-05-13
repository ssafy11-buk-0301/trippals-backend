package com.ssafy.trippals.user.dto;

import lombok.Data;

@Data
public class SignUpForm {
    private String name;
    private String password;
    private String email;
}
