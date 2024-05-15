package com.ssafy.trippals.route.dao;

import com.ssafy.trippals.attraction.dao.AttractionDao;
import com.ssafy.trippals.attraction.dto.RouteAttractionData;
import com.ssafy.trippals.route.dto.*;
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

    static List<RouteInsert> routeInsertList = List.of(
            new RouteInsert(1, "route1", "overview1", "thumbnail1", LocalDateTime.now()),
            new RouteInsert(1, "route2", "overview2", "thumbnail2", LocalDateTime.now()),
            new RouteInsert(1, "route3", "overview3", "thumbnail3", LocalDateTime.now()),
            new RouteInsert(1, "route4", "overview4", "thumbnail4", LocalDateTime.now()),
            new RouteInsert(2, "route5", "overview5", "thumbnail5", LocalDateTime.now()),
            new RouteInsert(2, "route6", "overview6", "thumbnail6", LocalDateTime.now()),
            new RouteInsert(3, "route7", "overview7", "thumbnail7", LocalDateTime.now()),
            new RouteInsert(3, "route8", "overview8", "thumbnail8", LocalDateTime.now()),
            new RouteInsert(3, "route9", "overview9", "thumbnail9", LocalDateTime.now()),
            new RouteInsert(3, "route10", "overview10", "thumbnail10", LocalDateTime.now())
    );

    static List<Integer> attractionInsertList = List.of(
            125266, 125405, 125406, 125407, 125408, 125409, 125410, 125411, 125412, 125413
    );

    @BeforeEach
    void setUp() {
        routeInsertList.forEach(routeDao::insertRoute);
    }

    @Test
    void findRouteDataByOwner() {
        // given
        int expectedOwner = 1;

        // when
        List<RouteData> actualRouteDataList = routeDao.findRouteDataByOwner(expectedOwner);

        // then
        assertThat(actualRouteDataList).allMatch(data -> data.getOwner() == expectedOwner);
    }

    @Test
    void updateRoute() {
        // given
        RouteInsert target = routeInsertList.get(0);
        RouteUpdate routeUpdate = new RouteUpdate(target.getSeq(), target.getOwner(),
                "modifiedName", "modifiedOverview", "modifiedThumbnail", LocalDate.now().atStartOfDay());

        // when
        int modifiedCount = routeDao.updateRoute(routeUpdate);
        Optional<RouteData> actual = routeDao.findRouteDataBySeq(target.getSeq());

        // then
        assertThat(modifiedCount).isEqualTo(1);
        assertThat(actual.isPresent()).isTrue();
        RouteData routeData = actual.get();

        assertThat(routeData.getName()).isEqualTo(routeUpdate.getName());
        assertThat(routeData.getOverview()).isEqualTo(routeUpdate.getOverview());
        assertThat(routeData.getThumbnail()).isEqualTo(routeUpdate.getThumbnail());
        assertThat(routeData.getStartDate()).isEqualTo(routeUpdate.getStartDate());
    }

    @Test
    void deleteRouteBySeq() {
        // given
        RouteInsert target = routeInsertList.get(0);

        // when
        int modifiedCount = routeDao.deleteRouteBySeq(target.getSeq());

        // then
        assertThat(modifiedCount).isEqualTo(1);
        assertThat(routeDao.findRouteDataBySeq(target.getSeq()).isEmpty()).isTrue();
    }

    @Test
    void insertAttractionIntoRoute() {
        // given
        RouteInsert target = routeInsertList.get(0);
        List<RouteAttractionInsert> insertList = attractionInsertList.stream()
                .map(id -> new RouteAttractionInsert(target.getSeq(), id))
                .toList();

        // when
        insertList.forEach(routeDao::insertAttractionIntoRoute);

        // then
        List<RouteAttractionData> actual = attractionDao.findByRouteSeq(target.getSeq());
        assertThat(actual).hasSize(insertList.size());
        assertThat(actual).allMatch(a -> attractionInsertList.contains(a.getContentId()));
    }

    @Test
    void deleteAttractionFromRoute() {
        // given
        RouteInsert targetRoute = routeInsertList.get(0);
        List<RouteAttractionInsert> insertList = attractionInsertList.stream()
                .map(id -> new RouteAttractionInsert(targetRoute.getSeq(), id))
                .toList();
        Integer targetAttraction = attractionInsertList.get(0);

        insertList.forEach(routeDao::insertAttractionIntoRoute);
        RouteAttractionDelete routeAttractionDelete = new RouteAttractionDelete(targetRoute.getSeq(), targetAttraction);

        // when
        routeDao.deleteAttractionFromRoute(routeAttractionDelete);

        // then
        List<RouteAttractionData> actual = attractionDao.findByRouteSeq(targetRoute.getSeq());
        assertThat(actual).hasSize(insertList.size()-1);
        assertThat(actual).noneMatch(a -> Objects.equals(a.getContentId(), targetAttraction));
    }

    @Test
    void updateRouteAttractionOrder() {
        // given
        RouteInsert target = routeInsertList.get(0);
        List<RouteAttractionInsert> insertList = attractionInsertList.stream()
                .map(id -> new RouteAttractionInsert(target.getSeq(), id))
                .toList();
        insertList.forEach(routeDao::insertAttractionIntoRoute);

        List<RouteAttractionData> before = attractionDao.findByRouteSeq(target.getSeq());
        RouteAttractionData attraction1 = before.get(0);
        RouteAttractionData attraction2 = before.get(1);

        RouteAttractionOrderUpdate orderUpdate1 =
                new RouteAttractionOrderUpdate(target.getSeq(), attraction1.getContentId(), attraction2.getOrderNumber());
        RouteAttractionOrderUpdate orderUpdate2 =
                new RouteAttractionOrderUpdate(target.getSeq(), attraction2.getContentId(), attraction1.getOrderNumber());

        // when
        routeDao.updateRouteAttractionOrder(orderUpdate1);
        routeDao.updateRouteAttractionOrder(orderUpdate2);

        // then
        List<RouteAttractionData> after = attractionDao.findByRouteSeq(target.getSeq());
        RouteAttractionData actual1 = after.get(0);
        RouteAttractionData actual2 = after.get(1);

        assertThat(actual1.getContentId()).isEqualTo(attraction2.getContentId());
        assertThat(actual2.getContentId()).isEqualTo(attraction1.getContentId());
    }
}