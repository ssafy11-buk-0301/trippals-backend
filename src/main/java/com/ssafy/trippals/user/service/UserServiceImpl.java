package com.ssafy.trippals.user.service;

import com.ssafy.trippals.common.exception.UserAlreadyExistsException;
import com.ssafy.trippals.common.exception.UserAuthException;
import com.ssafy.trippals.user.dao.UserDao;
import com.ssafy.trippals.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public Optional<UserDto> login(String email, String password) {
        return userDao.findUserDataByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .map(u -> { u.setPassword(null); return u; });
    }

    @Override
    public boolean signUp(UserDto userDto) {
        userDao.findUserDataByEmail(userDto.getEmail()).ifPresent(u -> {throw new UserAlreadyExistsException();});

        int modified = userDao.insertUser(userDto);

        if (modified > 0) {
            return true;
        } else {
            throw new UserAlreadyExistsException();
        }
    }

    @Override
    public Optional<UserDto> getUser(String email) {
        return userDao.findUserDataByEmail(email);
    }

    @Override
    public Optional<UserDto> updateUser(UserDto userDto) {
        userDao.updateUser(userDto);
        return getUser(userDto.getEmail());
    }

    @Override
    public boolean verifyPassword(String email, String password) {
        return userDao.findUserDataByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .isPresent();
    }

    @Override
    public boolean updateUserPassword(String email, String currentPassword, String newPassword) {
        if (!verifyPassword(email, currentPassword)) {
            throw new UserAuthException();
        }

        return userDao.updateUserPassword(email, newPassword) == 1;
    }
}
