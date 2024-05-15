package com.ssafy.trippals.route.dao;

import com.ssafy.trippals.route.dto.RouteData;
import com.ssafy.trippals.route.dto.RouteInsert;
import com.ssafy.trippals.route.dto.RouteUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RouteDaoTest {
    @Autowired RouteDao routeDao;

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
}