package com.ssafy.trippals.route.dao;

import com.ssafy.trippals.route.dto.RouteData;
import com.ssafy.trippals.route.dto.RouteInsert;
import com.ssafy.trippals.route.dto.RouteUpdate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RouteDao {
    List<RouteData> findRouteDataByOwner(Integer owner);
    int insertRoute(RouteInsert routeInsert);
    int updateRoute(RouteUpdate routeUpdate);
    int deleteRouteBySeq(Integer seq);
}
