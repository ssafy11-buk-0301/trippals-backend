package com.ssafy.trippals.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.trippals.common.exception.LoginException;
import com.ssafy.trippals.common.exception.dto.ErrorCode;
import com.ssafy.trippals.common.exception.dto.ErrorResult;
import com.ssafy.trippals.user.dto.UserInfo;
import com.ssafy.trippals.user.dto.UserInfoResponse;
import com.ssafy.trippals.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
        UserInfo expected = new UserInfo(1, "test", "test", "test");
        when(userService.login(any(String.class), any(String.class))).thenReturn(Optional.of(expected));
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("email", "test");
        params.add("password", "test");
        String expectedJson = objectMapper.writeValueAsString(new UserInfoResponse(expected));

        // then
        mockMvc.perform(post("/login").queryParams(params))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
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