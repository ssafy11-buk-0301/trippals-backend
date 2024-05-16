package com.ssafy.trippals.user.service;

import com.ssafy.trippals.user.dto.UserInfo;

import java.util.Optional;

public interface UserService {
    Optional<UserInfo> login(String email, String password);
    boolean signUp(UserInfo userInfo);
    Optional<UserInfo> getUser(String email);
    Optional<UserInfo> updateUser(UserInfo userInfo);
    boolean verifyPassword(String email, String password);
    boolean updateUserPassword(int seq, String currentPassword, String newPassword);
}
