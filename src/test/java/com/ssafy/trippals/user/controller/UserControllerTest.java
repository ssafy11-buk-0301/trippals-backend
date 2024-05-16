package com.ssafy.trippals.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.trippals.SessionConst;
import com.ssafy.trippals.common.exception.UserAuthException;
import com.ssafy.trippals.common.exception.dto.ErrorCode;
import com.ssafy.trippals.common.exception.dto.ErrorResult;
import com.ssafy.trippals.user.dto.UserInfo;
import com.ssafy.trippals.user.dto.UserInfoResponse;
import com.ssafy.trippals.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired MockMvc mvc;
    @MockBean UserService userService;
    @Autowired ObjectMapper objectMapper;

    @Test
    void searchUser() throws Exception {
        // given
        String email = "test";
        UserInfo userInfo = new UserInfo(1, "test", "test", "test");
        when(userService.getUser(any())).thenReturn(Optional.of(userInfo));

        // then
        mvc.perform(get("/users").param("email", email))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new UserInfoResponse(userInfo))));
    }

    @Test
    void getUser() throws Exception {
        // given
        UserInfo userInfo = new UserInfo(1, "test", "test", "test");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionConst.USER, userInfo);
        when(userService.getUser(any())).thenReturn(Optional.of(userInfo));

        // then
        mvc.perform(get("/users/1").session(session))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new UserInfoResponse(userInfo))));
    }

    @Test
    void getUserFail() throws Exception {
        // given
        ErrorResult expected = new ErrorResult(ErrorCode.UNAUTHORIZED, UserAuthException.message);

        // then
        mvc.perform(get("/users/1"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));
    }

    @Test
    void updateUser() throws Exception {
        // given
        UserInfo userInfo = new UserInfo(1, "test", "test", "test");
        UserInfo expected = new UserInfo(1, "test2", "test2", "test");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionConst.USER, userInfo);
        when(userService.updateUser(any())).thenAnswer((Answer<Optional<UserInfo>>) invocationOnMock -> Optional.of(invocationOnMock.getArgument(0, UserInfo.class)));
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", expected.getName());
        params.add("profileImage", expected.getProfileImageUri());

        // then
        mvc.perform(put("/users").params(params).session(session))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));
    }

    @Test
    void changePassword() throws Exception {
        // given
        UserInfo userInfo = new UserInfo(1, "test", "test", "test");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionConst.USER, userInfo);
        String currentPassword = "test";
        String newPassword = "test2";

        // then
        mvc.perform(put("/users/password")
                    .param("currentPassword", currentPassword)
                    .param("newPassword", newPassword).session(session))
                .andExpect(status().isOk());
    }
}