package com.ssafy.trippals.route.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.trippals.common.SessionConst;
import com.ssafy.trippals.attraction.dto.AttractionDto;
import com.ssafy.trippals.route.service.RouteAttractionService;
import com.ssafy.trippals.user.dto.UserDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RouteAttractionController.class)
class RouteAttractionControllerTest {
    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @MockBean RouteAttractionService routeAttractionService;
    static UserDto userInfo = new UserDto(1, "test", "test", "test", null, null);
    static MockHttpSession session = new MockHttpSession();

    @BeforeAll
    static void setUp() {
        session.setAttribute(SessionConst.USER, userInfo);
    }

    @Test
    void getRouteAttractions() throws Exception {
        // given
        List<AttractionDto> attractionDtoList = List.of(
                new AttractionDto(1, 1, "test", "test", "test", "test", BigDecimal.TEN, BigDecimal.TEN),
                new AttractionDto(1, 1, "test", "test", "test", "test", BigDecimal.TEN, BigDecimal.TEN),
                new AttractionDto(1, 1, "test", "test", "test", "test", BigDecimal.TEN, BigDecimal.TEN),
                new AttractionDto(1, 1, "test", "test", "test", "test", BigDecimal.TEN, BigDecimal.TEN)
        );
        when(routeAttractionService.getRouteAttractions(1, 1)).thenReturn(attractionDtoList);

        // then
        mockMvc.perform(get("/routes/1/attractions").session(session))
                .andExpect(content().json(objectMapper.writeValueAsString(attractionDtoList)));
    }

    @Test
    void addRouteAttraction() throws Exception {
        mockMvc.perform(post("/routes/1/attractions/1").session(session))
                .andExpect(status().isOk());
    }

    @Test
    void deleteRouteAttraction() throws Exception {
        mockMvc.perform(delete("/routes/1/attractions/1").session(session))
                .andExpect(status().isOk());
    }

    @Test
    void changeRouteAttractionOrder() throws Exception {
        mockMvc.perform(put("/routes/1/attractions/1/1").session(session))
                .andExpect(status().isOk());
    }

    @Test
    void getNearbyAttractions() throws Exception {
        mockMvc.perform(get("/routes/1/nearby-attractions").session(session))
                .andExpect(status().isOk());
    }
}