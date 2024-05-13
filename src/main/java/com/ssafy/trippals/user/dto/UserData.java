package com.ssafy.trippals.user.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserData {
    private Integer seq;

    private String name;
    private String password;
    private String email;
    private String profileImage;
    private LocalDate registerDate;
}
