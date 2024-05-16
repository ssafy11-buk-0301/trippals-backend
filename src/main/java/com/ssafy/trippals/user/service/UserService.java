package com.ssafy.trippals.user.service;

import com.ssafy.trippals.user.dto.UserInfo;

import java.util.Optional;

public interface UserService {
    Optional<UserInfo> login(String email, String password);
}
