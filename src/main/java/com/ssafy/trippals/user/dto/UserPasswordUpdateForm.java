package com.ssafy.trippals.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPasswordUpdateForm {
    private String currentPassword;
    private String modifyPassword;
}
