package com.ssafy.trippals.route.dao;

import com.ssafy.trippals.attraction.dao.AttractionDao;
import com.ssafy.trippals.attraction.dto.RouteAttractionDto;
import com.ssafy.trippals.route.dto.RouteDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RouteDaoTest {
    @Autowired RouteDao routeDao;
    @Autowired AttractionDao attractionDao;

    static List<RouteDto> routeInsertList = List.of(
            new RouteDto(1, "route1", "overview1", "thumbnail1", LocalDate.now()),
            new RouteDto(1, "route2", "overview2", "thumbnail2", LocalDate.now()),
            new RouteDto(1, "route3", "overview3", "thumbnail3", LocalDate.now()),
            new RouteDto(1, "route4", "overview4", "thumbnail4", LocalDate.now()),
            new RouteDto(2, "route5", "overview5", "thumbnail5", LocalDate.now()),
            new RouteDto(2, "route6", "overview6", "thumbnail6", LocalDate.now()),
            new RouteDto(3, "route7", "overview7", "thumbnail7", LocalDate.now()),
            new RouteDto(3, "route8", "overview8", "thumbnail8", LocalDate.now()),
            new RouteDto(3, "route9", "overview9", "thumbnail9", LocalDate.now()),
            new RouteDto(3, "route10", "overview10", "thumbnail10", LocalDate.now())
    );

    static List<Integer> attractionInsertList = List.of(
            125266, 125405, 125406, 125407, 125408, 125409, 125410, 125411, 125412, 125413
    );

    @BeforeEach
    void setUp() {
        routeInsertList.forEach(routeDao::insertRoute);
    }

    @Test
    void findRouteDtoByOwner() {
        // given
        int expectedOwner = 1;

        // when
        List<RouteDto> actualRouteDtoList = routeDao.findRouteDtoByOwner(expectedOwner);

        // then
        assertThat(actualRouteDtoList).allMatch(data -> data.getOwner() == expectedOwner);
    }

    @Test
    void updateRoute() {
        // given
        RouteDto target = routeInsertList.get(0);
        RouteDto routeUpdate = new RouteDto(target.getSeq(), target.getOwner(),
                "modifiedName", "modifiedOverview", "modifiedThumbnail", LocalDate.now());

        // when
        int modifiedCount = routeDao.updateRoute(routeUpdate);
        Optional<RouteDto> actual = routeDao.findRouteDtoBySeq(target.getSeq());

        // then
        assertThat(modifiedCount).isEqualTo(1);
        assertThat(actual.isPresent()).isTrue();
        RouteDto routeData = actual.get();

        assertThat(routeData.getName()).isEqualTo(routeUpdate.getName());
        assertThat(routeData.getOverview()).isEqualTo(routeUpdate.getOverview());
        assertThat(routeData.getThumbnail()).isEqualTo(routeUpdate.getThumbnail());
        assertThat(routeData.getStartDate()).isEqualTo(routeUpdate.getStartDate());
    }

    @Test
    void deleteRouteBySeq() {
        // given
        RouteDto target = routeInsertList.get(0);

        // when
        int modifiedCount = routeDao.deleteRouteBySeq(target.getSeq());

        // then
        assertThat(modifiedCount).isEqualTo(1);
        assertThat(routeDao.findRouteDtoBySeq(target.getSeq()).isEmpty()).isTrue();
    }

    @Test
    void insertAttractionIntoRoute() {
        // given
        RouteDto target = routeInsertList.get(0);

        // when
        attractionInsertList.forEach(contentId -> routeDao.insertAttractionIntoRoute(target.getSeq(), contentId));

        // then
        List<RouteAttractionDto> actual = attractionDao.findByRouteSeq(target.getSeq());
        assertThat(actual).hasSize(attractionInsertList.size());
        assertThat(actual).allMatch(a -> attractionInsertList.contains(a.getContentId()));
    }

    @Test
    void deleteAttractionFromRoute() {
        // given
        RouteDto targetRoute = routeInsertList.get(0);
        Integer targetAttraction = attractionInsertList.get(0);

        attractionInsertList.forEach(contentId -> routeDao.insertAttractionIntoRoute(targetRoute.getSeq(), contentId));

        // when
        routeDao.deleteAttractionFromRoute(targetRoute.getSeq(), targetAttraction);

        // then
        List<RouteAttractionDto> actual = attractionDao.findByRouteSeq(targetRoute.getSeq());
        assertThat(actual).hasSize(attractionInsertList.size()-1);
        assertThat(actual).noneMatch(a -> Objects.equals(a.getContentId(), targetAttraction));
    }

    @Test
    void updateRouteAttractionOrder() {
        // given
        RouteDto target = routeInsertList.get(0);
        attractionInsertList.forEach(contentId -> routeDao.insertAttractionIntoRoute(target.getSeq(), contentId));

        List<RouteAttractionDto> before = attractionDao.findByRouteSeq(target.getSeq());
        RouteAttractionDto attraction1 = before.get(0);
        RouteAttractionDto attraction2 = before.get(1);

        // when
        routeDao.updateRouteAttractionOrder(target.getSeq(), attraction1.getContentId(), attraction2.getOrderNumber());
        routeDao.updateRouteAttractionOrder(target.getSeq(), attraction2.getContentId(), attraction1.getOrderNumber());

        // then
        List<RouteAttractionDto> after = attractionDao.findByRouteSeq(target.getSeq());
        RouteAttractionDto actual1 = after.get(0);
        RouteAttractionDto actual2 = after.get(1);

        assertThat(actual1.getContentId()).isEqualTo(attraction2.getContentId());
        assertThat(actual2.getContentId()).isEqualTo(attraction1.getContentId());
    }
}