package com.ssafy.trippals.attraction.dao;

import com.ssafy.trippals.attraction.dto.AttractionDto;
import com.ssafy.trippals.attraction.dto.AttractionSearchParams;
import com.ssafy.trippals.attraction.dto.ContentType;
import com.ssafy.trippals.attraction.dto.NearByAttractionContentTypeSelect;
import com.ssafy.trippals.common.page.dto.PageParams;
import com.ssafy.trippals.route.dao.RouteDao;
import com.ssafy.trippals.route.dto.RouteDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AttractionDaoTest {
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
    void findByKeyword() {
        // given
        String expected = "국립";
        AttractionSearchParams params = new AttractionSearchParams(pageParams, expected);

        // when
        List<AttractionDto> actual = attractionDao.findByKeyword(params);

        // then
        assertThat(actual).isNotEmpty();
        assertThat(actual).allMatch(a -> a.getTitle().contains(expected));
    }

    @Test
    void findBySidoAndKeyword() {
        // given
        String keyword = "국립";
        int sidocode = 32;
        AttractionSearchParams attractionSidoAndKeywordSelect = new AttractionSearchParams(pageParams, keyword, sidocode);

        // when
        List<AttractionDto> actual = attractionDao.findBySidoAndKeyword(attractionSidoAndKeywordSelect);

        // then
        assertThat(actual).isNotEmpty();
        assertThat(actual).allMatch(a -> a.getTitle().contains(keyword));
    }

    @Test
    void findByGugunAndKeyword() {
        // given
        String keyword = "국립";
        int sidocode = 32;
        int guguncode = 10;
        AttractionSearchParams attractionGugunAndKeywordSelect = new AttractionSearchParams(pageParams, sidocode, guguncode, keyword);

        // when
        List<AttractionDto> actual = attractionDao.findByGugunAndKeyword(attractionGugunAndKeywordSelect);

        // then
        assertThat(actual).isNotEmpty();
        assertThat(actual).allMatch(a -> a.getTitle().contains(keyword));
    }

    @Test
    void findNearbyAttractionsByContentType() {
        // given
        ContentType expected = ContentType.FESTIVAL;
        NearByAttractionContentTypeSelect contentTypeSelect = new NearByAttractionContentTypeSelect(0, 10, 1, expected.getCode());

        // when
        List<AttractionDto> actual = attractionDao.findNearbyAttractionsByContentType(contentTypeSelect);

        // then
        assertThat(actual).isNotEmpty();
        assertThat(actual).allMatch(a -> a.getContentTypeId().equals(expected.getCode()));
    }
}