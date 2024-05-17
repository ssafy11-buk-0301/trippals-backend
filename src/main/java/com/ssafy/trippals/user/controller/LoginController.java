package com.ssafy.trippals.user.controller;

import com.ssafy.trippals.SessionConst;
import com.ssafy.trippals.common.exception.LoginException;
import com.ssafy.trippals.user.dto.LoginForm;
import com.ssafy.trippals.user.dto.UserDto;
import com.ssafy.trippals.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(HttpSession session, @ModelAttribute LoginForm loginForm) {
        Optional<UserDto> optionalUserDto = userService.login(loginForm.getEmail(), loginForm.getPassword());

        return optionalUserDto
                .map(u -> setSession(session, u))
                .map(ResponseEntity::ok)
                .orElseThrow(LoginException::new);
    }

    private UserDto setSession(HttpSession session, UserDto userDto) {
        session.setAttribute(SessionConst.USER, userDto);
        return userDto;
    }
}
