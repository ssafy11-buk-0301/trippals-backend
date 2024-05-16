package com.ssafy.trippals.user.controller;

import com.ssafy.trippals.user.dto.SignUpForm;
import com.ssafy.trippals.user.dto.SignUpInfo;
import com.ssafy.trippals.user.dto.UserInfo;
import com.ssafy.trippals.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignUpController {
    private final UserService userService;

    @PostMapping("/signUp")
    @ResponseStatus(HttpStatus.OK)
    public void signUp(@ModelAttribute SignUpForm signUpForm) {
        userService.signUp(new SignUpInfo(signUpForm));
    }
}
