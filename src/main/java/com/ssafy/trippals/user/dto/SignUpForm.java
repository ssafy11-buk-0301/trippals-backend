package com.ssafy.trippals.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpForm {
    private String name;
    private String password;
    private String email;
}
