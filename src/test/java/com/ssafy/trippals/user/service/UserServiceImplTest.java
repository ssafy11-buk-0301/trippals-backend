package com.ssafy.trippals.user.service;

import com.ssafy.trippals.user.dao.UserDao;
import com.ssafy.trippals.user.dto.SignUpForm;
import com.ssafy.trippals.user.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
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
                .thenReturn(Optional.of(new UserDto(1, "test", "test", "test", "test", now())));

        // when
        Optional<UserDto> optionalUserDto = userService.login(email, password);

        // then
        assertThat(optionalUserDto.isPresent()).isTrue();
        assertThat(optionalUserDto.get().getEmail()).isEqualTo(email);
    }

    @Test
    void signUp() {
        // given
        UserDto signUpInfo = new UserDto(new SignUpForm("test", "test", "test"));
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
                .thenReturn(Optional.of(new UserDto(1, "test", "test", email, "test", now())));

        // when
        Optional<UserDto> optionalUserDto = userService.getUser(email);

        // then
        assertThat(optionalUserDto.isPresent()).isTrue();
        assertThat(optionalUserDto.get().getEmail()).isEqualTo(email);
    }

    @Test
    void updateUser() {
        // given
        UserDto userDto = new UserDto(1, "test", "test", "test", "test", now());
        when(userDao.findUserDataByEmail(any()))
                .thenReturn(Optional.of(userDto));

        // when
        Optional<UserDto> actual = userService.updateUser(userDto);

        // then
        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get().getEmail()).isEqualTo(userDto.getEmail());
    }

    @Test
    void verifyPassword() {
        // given
        UserDto userDto = new UserDto(1, "test", "test", "test", "test", now());
        when(userDao.findUserDataByEmail(any()))
                .thenReturn(Optional.of(userDto));

        // when
        boolean result = userService.verifyPassword(userDto.getEmail(), "test");

        // then
        assertThat(result).isTrue();
    }

    @Test
    void updateUserPassword() {
        // given
        UserDto userDto = new UserDto(1, "test", "test", "test", "test", now());
        when(userDao.findUserDataByEmail(any()))
                .thenReturn(Optional.of(userDto));
        when(userDao.updateUserPassword(any(), any())).thenReturn(1);

        // when
        boolean result = userService.updateUserPassword(userDto.getEmail(), "test", "test");

        // then
        assertThat(result).isTrue();
    }
}