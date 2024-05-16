package com.ssafy.trippals.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.trippals.common.exception.UserAlreadyExistsException;
import com.ssafy.trippals.common.exception.dto.ErrorCode;
import com.ssafy.trippals.common.exception.dto.ErrorResult;
import com.ssafy.trippals.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SignUpController.class)
class SignUpControllerTest {
    @MockBean UserService userService;
    @Autowired ObjectMapper objectMapper;
    @Autowired MockMvc mockMvc;

    @Test
    public void signUp() throws Exception {
        // given
        when(userService.signUp(any())).thenReturn(true);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "test");
        params.add("password", "test");
        params.add("email", "test");

        // then
        mockMvc.perform(post("/signUp").params(params))
                .andExpect(status().isOk());
    }

    @Test
    public void signFail() throws Exception {
        // given
        when(userService.signUp(any())).thenThrow(new UserAlreadyExistsException());
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "test");
        params.add("password", "test");
        params.add("email", "test");
        ErrorResult expected = new ErrorResult(ErrorCode.USER_ALREADY_EXIST, UserAlreadyExistsException.message);

        // then
        mockMvc.perform(post("/signUp").params(params))
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));
    }
}