package com.ssafy.trippals.user.controller;

import com.ssafy.trippals.SessionConst;
import com.ssafy.trippals.common.exception.UserAuthException;
import com.ssafy.trippals.common.exception.UserNotFoundException;
import com.ssafy.trippals.user.dto.UserInfo;
import com.ssafy.trippals.user.dto.UserInfoResponse;
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
    public ResponseEntity<UserInfoResponse> searchUser(@RequestParam("email") String email) {
        return ResponseEntity.ok(userService.getUser(email).map(UserInfoResponse::new).orElse(null));
    }

    @GetMapping("/{userSeq}")
    public ResponseEntity<UserInfoResponse> getUser(
            @SessionAttribute(value = SessionConst.USER, required = false) UserInfo userInfo
    ) {
        if (userInfo == null) {
            throw new UserAuthException();
        }

        return userService.getUser(userInfo.getEmail())
                .map(UserInfoResponse::new)
                .map(ResponseEntity::ok)
                .orElseThrow(UserNotFoundException::new);
    }

    @PutMapping
    public ResponseEntity<UserInfoResponse> updateUser(
            @SessionAttribute(value = SessionConst.USER, required = false) UserInfo userInfo,
            @ModelAttribute UserUpdateForm userUpdateForm
    ) {
        if (userInfo == null) {
            throw new UserAuthException();
        }

        UserInfo userInfoUpdate =
                new UserInfo(userInfo.getSeq(), userUpdateForm.getName(), userUpdateForm.getProfileImage(), userInfo.getEmail());

        Optional<UserInfo> optionalUserInfo = userService.updateUser(userInfoUpdate);

        return optionalUserInfo
                .map(UserInfoResponse::new)
                .map(ResponseEntity::ok)
                .orElseThrow(UserNotFoundException::new);
    }

    @PutMapping("/password")
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(
            @SessionAttribute(value = SessionConst.USER, required = false) UserInfo userInfo,
            @ModelAttribute UserPasswordUpdateForm userPasswordUpdateForm
    ) {
        if (userInfo == null) {
            throw new UserAuthException();
        }

        userService.updateUserPassword(userInfo.getSeq(),
                userPasswordUpdateForm.getCurrentPassword(),
                userPasswordUpdateForm.getModifyPassword());
    }
}
