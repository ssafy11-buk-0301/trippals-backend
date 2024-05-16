package com.ssafy.trippals.user.controller;

import com.ssafy.trippals.common.exception.LoginException;
import com.ssafy.trippals.user.dto.LoginForm;
import com.ssafy.trippals.user.dto.UserInfo;
import com.ssafy.trippals.user.dto.UserInfoResponse;
import com.ssafy.trippals.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserInfoResponse> login(@ModelAttribute LoginForm loginForm) {
        Optional<UserInfo> optionalUserInfo = userService.login(loginForm.getEmail(), loginForm.getPassword());

        return optionalUserInfo
                .map(UserInfoResponse::new)
                .map(ResponseEntity::ok)
                .orElseThrow(LoginException::new);
    }
}
