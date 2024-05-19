package com.ssafy.trippals.user.controller;

import com.ssafy.trippals.SessionConst;
import com.ssafy.trippals.common.exception.UserAuthException;
import com.ssafy.trippals.common.exception.UserNotFoundException;
import com.ssafy.trippals.user.dto.UserDto;
import com.ssafy.trippals.user.dto.UserPasswordUpdateForm;
import com.ssafy.trippals.user.dto.UserUpdateForm;
import com.ssafy.trippals.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserDto> searchUser(@RequestParam("email") String email) {
        return ResponseEntity.ok(userService.getUser(email).orElse(null));
    }

    @GetMapping("/{userSeq}")
    public ResponseEntity<UserDto> getUser(
            @SessionAttribute(value = SessionConst.USER, required = false) UserDto userDto
    ) {
        if (userDto == null) {
            throw new UserAuthException();
        }

        return userService.getUser(userDto.getEmail())
                .map(ResponseEntity::ok)
                .orElseThrow(UserNotFoundException::new);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(
            @SessionAttribute(value = SessionConst.USER, required = false) UserDto userDto,
            @RequestBody UserUpdateForm userUpdateForm
    ) {
        if (userDto == null) {
            throw new UserAuthException();
        }

        Optional<UserDto> optionalUserDto = userService.updateUser(new UserDto(userDto.getSeq(), userUpdateForm));

        return optionalUserDto
                .map(ResponseEntity::ok)
                .orElseThrow(UserNotFoundException::new);
    }

    @PutMapping("/password")
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(
            @SessionAttribute(value = SessionConst.USER, required = false) UserDto userDto,
            @RequestBody UserPasswordUpdateForm userPasswordUpdateForm
    ) {
        if (userDto == null) {
            throw new UserAuthException();
        }

        userService.updateUserPassword(userDto.getEmail(),
                userPasswordUpdateForm.getCurrentPassword(),
                userPasswordUpdateForm.getNewPassword());
    }
}
