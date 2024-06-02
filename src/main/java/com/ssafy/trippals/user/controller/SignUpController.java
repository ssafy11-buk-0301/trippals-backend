//package com.ssafy.trippals.user.controller;
//
//import com.ssafy.trippals.common.exception.UserAlreadyExistsException;
//import com.ssafy.trippals.user.dto.SignUpForm;
//import com.ssafy.trippals.user.dto.UserDto;
//import com.ssafy.trippals.user.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//public class SignUpController {
//    private final UserService userService;
//
//    @PostMapping("/signUp")
//    @ResponseStatus(HttpStatus.OK)
//    public void signUp(@RequestBody SignUpForm signUpForm) {
//        userService.signUp(new UserDto(signUpForm));
//    }
//
//    @GetMapping("/signUp/confirm")
//    @ResponseStatus(HttpStatus.OK)
//    public void confirm(@RequestParam("email") String email) {
//        userService.getUser(email).ifPresent((u) -> { throw new UserAlreadyExistsException(); });
//    }
//}
