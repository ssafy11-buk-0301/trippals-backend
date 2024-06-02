package com.ssafy.trippals.route.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.trippals.common.SessionConst;
import com.ssafy.trippals.route.service.RouteService;
import com.ssafy.trippals.user.dto.UserDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static java.time.LocalDateTime.now;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RouteController.class)
class RouteControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    RouteService routeService;
    static UserDto userInfo = new UserDto(1, "test", "test", "test", null, null);
    static MockHttpSession session = new MockHttpSession();

    @BeforeAll
    static void setUp() {
        session.setAttribute(SessionConst.USER, userInfo);
    }

    @Test
    void createRoute() throws Exception {
        // given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "test");
        params.add("overview", "test");
        params.add("thumbnail", "test");
        params.add("startDate", now().toString());
        mockMvc.perform(post("/routes").session(session)
                        .params(params))
                .andExpect(status().isOk());
    }

    @Test
    void getRoutes() throws Exception {
        mockMvc.perform(get("/routes").session(session))
                .andExpect(status().isOk());
    }

    @Test
    void updateRoute() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "test");
        params.add("overview", "test");
        params.add("thumbnail", "test");
        params.add("startDate", now().toString());

        mockMvc.perform(put("/routes/1").session(session).params(params))
                .andExpect(status().isOk());
    }

    @Test
    void deleteRoute() throws Exception {
        mockMvc.perform(delete("/routes/1").session(session))
                .andExpect(status().isOk());
    }
}