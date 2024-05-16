package com.ssafy.trippals.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    private Integer seq;

    private String name;
    private String password;
    private String email;
    private String profileImage;
    private LocalDate registerDate;
}
