package com.ssafy.trippals.user.service;

import com.ssafy.trippals.user.dto.UserDto;

import java.util.Optional;

public interface UserService {
    Optional<UserDto> login(String email, String password);
    boolean signUp(UserDto userDto);
    Optional<UserDto> getUser(String email);
    Optional<UserDto> updateUser(UserDto UserDto);
    boolean verifyPassword(String email, String password);
    boolean updateUserPassword(String email, String currentPassword, String newPassword);
}
