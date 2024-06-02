package com.ssafy.trippals.route.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.trippals.common.SessionConst;
import com.ssafy.trippals.common.page.dto.PageResponse;
import com.ssafy.trippals.route.dao.RouteDao;
import com.ssafy.trippals.route.dao.RouteEditorDao;
import com.ssafy.trippals.route.dto.RouteDto;
import com.ssafy.trippals.route.dto.RouteEditorDto;
import com.ssafy.trippals.route.dto.RouteEditorRequestDto;
import com.ssafy.trippals.user.dao.UserDao;
import com.ssafy.trippals.user.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class RouteEditorControllerTest {
    @Autowired UserDao userDao;
    @Autowired RouteDao routeDao;
    @Autowired MockMvc mvc;
    @Autowired ObjectMapper objectMapper;

    static List<UserDto> users =List.of(
            new UserDto("owner", "test", "testEmail1", "test", LocalDate.now()),
            new UserDto("editor", "test", "testEmail2", "test", LocalDate.now()),
            new UserDto("none", "test", "testEmail3", "test", LocalDate.now())
            );;
    static RouteDto route;
    @Autowired
    private RouteEditorDao routeEditorDao;

    @BeforeEach
    public void setUp() {
        users.forEach(userDao::insertUser);
        route = new RouteDto(users.get(0).getSeq(), "test", "test", "test", LocalDate.now());
        routeDao.insertRoute(route);

        routeEditorDao.insertRouteEditor(new RouteEditorDto(route.getSeq(), users.get(1).getSeq()));
    }

    @Test
    void getRouteEditors() throws Exception {
        // given
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionConst.USER, users.get(1));

        // then
        mvc.perform(
                get(String.format("/routes/%d/editors", route.getSeq()))
                    .session(session)
                    .param("editorSeq", Integer.toString(users.get(1).getSeq()))
        ).andExpect(status().isOk());

    }

    @Test
    void deleteRouteEditor() throws Exception {
        // given
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionConst.USER, users.get(0));

        // then
        mvc.perform(
                delete(String.format("/routes/%d/editors", route.getSeq()))
                        .session(session)
                        .param("editorSeq", Integer.toString(users.get(1).getSeq()))
        ).andExpect(status().isOk());
    }

    @Test
    void getEditableRoute() throws Exception {
        // given
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionConst.USER, users.get(1));
        PageResponse<RouteDto> expected = new PageResponse<>(List.of(route), 0, 10, 1);

        // then
        mvc.perform(
                get("/editors/routes")
                .session(session)
        ).andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(expected)));
    }

    @Test
    void makeRequest() throws Exception {
        // given
        UserDto owner = users.get(0);
        UserDto editor = users.get(1);
        UserDto none = users.get(2);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionConst.USER, owner);

        // then
        mvc.perform(
                        post(String.format("/routes/%d/editors", route.getSeq()))
                                .param("editorSeq", Integer.toString(editor.getSeq()))
                                .session(session)
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("104"));

        mvc.perform(
                        post(String.format("/routes/%d/editors", route.getSeq()))
                                .param("editorSeq", Integer.toString(none.getSeq()))
                                .session(session)
                ).andExpect(status().isOk());
    }

    @Test
    void confirmOrReject() throws Exception {
        // given
        UserDto owner = users.get(0);
        UserDto editor = users.get(1);
        UserDto none = users.get(2);

        MockHttpSession sessionNone = new MockHttpSession();
        MockHttpSession sessionEditor = new MockHttpSession();
        sessionNone.setAttribute(SessionConst.USER, none);
        sessionEditor.setAttribute(SessionConst.USER, none);

        routeEditorDao.insertRouteEditorRequest(new RouteEditorRequestDto(route.getSeq(), null, none.getSeq()));

        // then
        mvc.perform(
                post(String.format("/routes/%d/editors/confirm", route.getSeq()))
                        .session(sessionNone)
        ).andExpect(status().isOk());

        mvc.perform(
                post(String.format("/routes/%d/editors/confirm", route.getSeq()))
                        .session(sessionEditor)
        ).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.errorCode").value("104"));
    }
}