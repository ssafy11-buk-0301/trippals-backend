package com.ssafy.trippals.user.service;

import com.ssafy.trippals.user.dto.SignUpInfo;
import com.ssafy.trippals.user.dto.UserInfo;

import java.util.Optional;

public interface UserService {
    Optional<UserInfo> login(String email, String password);
    boolean signUp(SignUpInfo signUpInfo);
    Optional<UserInfo> getUser(String email);
    Optional<UserInfo> updateUser(UserInfo userInfo);
    boolean verifyPassword(String email, String password);
    boolean updateUserPassword(String email, String currentPassword, String newPassword);
}
