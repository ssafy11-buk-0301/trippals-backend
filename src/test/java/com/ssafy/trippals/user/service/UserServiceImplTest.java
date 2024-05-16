package com.ssafy.trippals.user.service;

import com.ssafy.trippals.user.dao.UserDao;
import com.ssafy.trippals.user.dto.SignUpInfo;
import com.ssafy.trippals.user.dto.UserData;
import com.ssafy.trippals.user.dto.UserInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UserDao userDao;

    @Test
    void login() {
        // given
        String email = "test";
        String password = "test";
        when(userDao.findUserDataByEmail(any()))
                .thenReturn(Optional.of(new UserData(1, "test", "test", "test", "test", now())));

        // when
        Optional<UserInfo> optionalUserInfo = userService.login(email, password);

        // then
        assertThat(optionalUserInfo.isPresent()).isTrue();
        assertThat(optionalUserInfo.get().getEmail()).isEqualTo(email);
    }

    @Test
    void signUp() {
        // given
        SignUpInfo signUpInfo = new SignUpInfo("test", "test", "test");
        when(userDao.findUserDataByEmail(any())).thenReturn(Optional.empty());
        when(userDao.insertUser(any())).thenReturn(1);

        // when
        boolean result = userService.signUp(signUpInfo);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void getUser() {
        // given
        String email = "test";
        when(userDao.findUserDataByEmail(any()))
                .thenReturn(Optional.of(new UserData(1, "test", "test", email, "test", now())));

        // when
        Optional<UserInfo> optionalUserInfo = userService.getUser(email);

        // then
        assertThat(optionalUserInfo.isPresent()).isTrue();
        assertThat(optionalUserInfo.get().getEmail()).isEqualTo(email);
    }

    @Test
    void updateUser() {
        // given
        UserData userData = new UserData(1, "test", "test", "test", "test", now());
        UserInfo userInfo = new UserInfo(userData);
        when(userDao.findUserDataByEmail(any()))
                .thenReturn(Optional.of(userData));

        // when
        Optional<UserInfo> actual = userService.updateUser(userInfo);

        // then
        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get().getEmail()).isEqualTo(userData.getEmail());
    }

    @Test
    void verifyPassword() {
        // given
        UserData userData = new UserData(1, "test", "test", "test", "test", now());
        when(userDao.findUserDataByEmail(any()))
                .thenReturn(Optional.of(userData));

        // when
        boolean result = userService.verifyPassword(userData.getEmail(), "test");

        // then
        assertThat(result).isTrue();
    }

    @Test
    void updateUserPassword() {
        // given
        UserData userData = new UserData(1, "test", "test", "test", "test", now());
        when(userDao.findUserDataByEmail(any()))
                .thenReturn(Optional.of(userData));
        when(userDao.updateUserPassword(any(), any())).thenReturn(1);

        // when
        boolean result = userService.updateUserPassword(userData.getEmail(), "test", "test");

        // then
        assertThat(result).isTrue();
    }
}