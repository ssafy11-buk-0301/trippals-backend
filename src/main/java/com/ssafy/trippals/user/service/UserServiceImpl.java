package com.ssafy.trippals.user.service;

import com.ssafy.trippals.common.exception.UserAlreadyExistsException;
import com.ssafy.trippals.common.exception.UserAuthException;
import com.ssafy.trippals.common.exception.UserNotFoundException;
import com.ssafy.trippals.user.dao.UserDao;
import com.ssafy.trippals.user.dto.SignUpInfo;
import com.ssafy.trippals.user.dto.UserInfo;
import com.ssafy.trippals.user.dto.UserInsert;
import com.ssafy.trippals.user.dto.UserUpdate;
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
    public Optional<UserInfo> login(String email, String password) {
        return userDao.findUserDataByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .map(UserInfo::new);
    }

    @Override
    public boolean signUp(SignUpInfo signUpInfo) {
        userDao.findUserDataByEmail(signUpInfo.getEmail()).ifPresent(u -> {throw new UserAlreadyExistsException();});

        int modified = userDao.insertUser(new UserInsert(signUpInfo));

        if (modified > 0) {
            return true;
        } else {
            throw new UserAlreadyExistsException();
        }
    }

    @Override
    public Optional<UserInfo> getUser(String email) {
        return userDao.findUserDataByEmail(email).map(UserInfo::new);
    }

    @Override
    public Optional<UserInfo> updateUser(UserInfo userInfo) {
        userDao.updateUser(new UserUpdate(userInfo));
        return getUser(userInfo.getEmail());
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
