package com.ssafy.trippals.user.service;

import com.ssafy.trippals.user.dto.UserInfo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public Optional<UserInfo> login(String email, String password) {
        return Optional.empty();
    }

    @Override
    public boolean signUp(UserInfo userInfo) {
        return false;
    }

    @Override
    public Optional<UserInfo> getUser(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<UserInfo> updateUser(UserInfo userInfo) {
        return Optional.empty();
    }

    @Override
    public boolean verifyPassword(String email, String password) {
        return false;
    }

    @Override
    public boolean updateUserPassword(int seq, String currentPassword, String modifyPassword) {
        return false;
    }
}
