package com.ssafy.trippals.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.trippals.common.exception.LoginException;
import com.ssafy.trippals.common.exception.dto.ErrorCode;
import com.ssafy.trippals.common.exception.dto.ErrorResult;
import com.ssafy.trippals.user.dto.UserDto;
import com.ssafy.trippals.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

import static java.time.LocalDate.now;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
class LoginControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @MockBean UserService userService;

    @Test
    void login() throws Exception {
        // given
        UserDto expected = new UserDto(1, "test", "test", "test", "test", now());
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("email", "test");
        params.add("password", "test");
        expected.setPassword(null);
        expected.setPassword("test");
        String expectedJson = objectMapper.writeValueAsString(expected);
        when(userService.login(any(String.class), any(String.class))).thenReturn(Optional.of(expected));


        // then
        mockMvc.perform(post("/login").queryParams(params))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson))
                .andDo(System.out::println);
    }

    @Test
    void loginFail() throws Exception {
        // given
        ErrorResult errorResult = new ErrorResult(ErrorCode.LOGIN_FAIL, LoginException.message);
        when(userService.login(any(String.class), any(String.class))).thenReturn(Optional.empty());
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("email", "test");
        params.add("password", "test");
        String expectedJson = objectMapper.writeValueAsString(errorResult);

        // then
        mockMvc.perform(post("/login").queryParams(params))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedJson));
    }
}