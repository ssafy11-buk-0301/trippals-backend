package com.ssafy.trippals.attraction.dao;

import com.ssafy.trippals.attraction.dto.*;
import com.ssafy.trippals.route.dao.RouteDao;
import com.ssafy.trippals.route.dto.RouteAttractionInsert;
import com.ssafy.trippals.route.dto.RouteInsert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AttractionDaoTest {
    @Autowired AttractionDao attractionDao;
    @Autowired RouteDao routeDao;

    static RouteInsert routeInsert = new RouteInsert(1, "route1", "overview1", "thumbnail1", LocalDateTime.now());

    static List<Integer> attractionInsertList = List.of(
            125266, 125405, 125406, 125407, 125408, 125409, 125410, 125411, 125412, 125413
    );

    @BeforeEach
    void setUp() {
        routeDao.insertRoute(routeInsert);
        List<RouteAttractionInsert> insertList = attractionInsertList.stream()
                .map(id -> new RouteAttractionInsert(routeInsert.getSeq(), id))
                .toList();
        insertList.forEach(routeDao::insertAttractionIntoRoute);
    }

    @Test
    void findByKeyword() {
        // given
        String expected = "국립";
        AttractionKeywordSelect attractionKeywordSelect = new AttractionKeywordSelect(0, 10, expected);

        // when
        List<AttractionData> actual = attractionDao.findByKeyword(attractionKeywordSelect);

        // then
        assertThat(actual).isNotEmpty();
        assertThat(actual).allMatch(a -> a.getTitle().contains(expected));
    }

    @Test
    void findBySidoAndKeyword() {
        // given
        String keyword = "국립";
        int sidocode = 32;
        AttractionSidoAndKeywordSelect attractionSidoAndKeywordSelect = new AttractionSidoAndKeywordSelect(0, 10, sidocode, keyword);

        // when
        List<AttractionData> actual = attractionDao.findBySidoAndKeyword(attractionSidoAndKeywordSelect);

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
        AttractionGugunAndKeywordSelect attractionGugunAndKeywordSelect = new AttractionGugunAndKeywordSelect(0, 10, sidocode, guguncode, keyword);

        // when
        List<AttractionData> actual = attractionDao.findByGugunAndKeyword(attractionGugunAndKeywordSelect);

        // then
        assertThat(actual).isNotEmpty();
        assertThat(actual).allMatch(a -> a.getTitle().contains(keyword));
    }

    @Test
    void findNearbyAttractionsByContentType() {
        // given
        ContentType expected = ContentType.FESTIVAL;
        NearByAttractionContentTypeSelect contentTypeSelect = new NearByAttractionContentTypeSelect(0, 10, 1, expected);

        // when
        List<AttractionData> actual = attractionDao.findNearbyAttractionsByContentType(contentTypeSelect);

        // then
        assertThat(actual).isNotEmpty();
        assertThat(actual).allMatch(a -> a.getContentTypeId().equals(expected.getCode()));
    }
}