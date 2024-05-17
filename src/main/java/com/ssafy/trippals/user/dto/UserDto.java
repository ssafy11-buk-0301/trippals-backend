package com.ssafy.trippals.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer seq;

    private String name;
    private String password;
    private String email;
    private String profileImage;
    private LocalDate registerDate;

    public UserDto(String name, String password, String email, String profileImage, LocalDate registerDate) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.profileImage = profileImage;
        this.registerDate = registerDate;
    }

    public UserDto(SignUpForm signUpForm) {
        this.name = signUpForm.getName();
        this.password = signUpForm.getPassword();
        this.email = signUpForm.getEmail();
    }

    public UserDto(Integer seq, UserUpdateForm userUpdateForm) {
        this.seq = seq;
        this.name = userUpdateForm.getName();
        this.profileImage = userUpdateForm.getProfileImage();
    }
}
