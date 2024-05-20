package com.ssafy.trippals.attraction.controller;

import com.ssafy.trippals.attraction.dao.AttractionDao;
import com.ssafy.trippals.attraction.dto.AttractionDto;
import com.ssafy.trippals.attraction.dto.AttractionSearchParams;
import com.ssafy.trippals.common.page.dto.PageParams;
import com.ssafy.trippals.common.page.dto.PageResponse;
import com.ssafy.trippals.route.dao.RouteDao;
import com.ssafy.trippals.route.dto.RouteDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AttractionControllerTest {
    @Autowired AttractionController attractionController;
    @Autowired AttractionDao attractionDao;
    @Autowired RouteDao routeDao;

    static RouteDto routeInsert = new RouteDto(1, "route1", "overview1", "thumbnail1", LocalDate.now());

    static List<Integer> attractionInsertList = List.of(
            125266, 125405, 125406, 125407, 125408, 125409, 125410, 125411, 125412, 125413
    );

    static PageParams pageParams = new PageParams(0, 10, "");

    @BeforeEach
    void setUp() {
        routeDao.insertRoute(routeInsert);
        attractionInsertList.forEach(contentId -> routeDao.insertAttractionIntoRoute(routeInsert.getSeq(), contentId));
    }

    @Test
    void searchAttractionByKeyword() {
        // given
        String expected = "국립";
        AttractionSearchParams params = new AttractionSearchParams(pageParams, expected);

        // when
        ResponseEntity<PageResponse<AttractionDto>> response = attractionController.searchAttractions(params);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        PageResponse<AttractionDto> pageResponse = response.getBody();
        assertThat(pageResponse.getContents()).allMatch(attractionDto -> attractionDto.getTitle().contains(expected));
    }

    @Test
    void searchAttractionBySidoKeyword() {
        // given
        String expected = "국립";
        int sidocode = 35;
        AttractionSearchParams params = new AttractionSearchParams(pageParams, expected, sidocode);

        // when
        ResponseEntity<PageResponse<AttractionDto>> response = attractionController.searchAttractions(params);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        PageResponse<AttractionDto> pageResponse = response.getBody();
        assertThat(pageResponse.getContents())
                .allMatch(attractionDto -> attractionDto.getTitle().contains(expected));
    }

    @Test
    void searchAttractionByGugunKeyword() {
        // given
        String expected = "국립";
        int sidocode = 35;
        int guguncode = 21;
        AttractionSearchParams params = new AttractionSearchParams(pageParams, expected, sidocode, guguncode);

        // when
        ResponseEntity<PageResponse<AttractionDto>> response = attractionController.searchAttractions(params);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        PageResponse<AttractionDto> pageResponse = response.getBody();
        assertThat(pageResponse.getContents())
                .allMatch(attractionDto -> attractionDto.getTitle().contains(expected));
    }
}