package com.ssafy.trippals.route.service;

import com.ssafy.trippals.route.dto.RouteInfo;
import com.ssafy.trippals.route.dto.RouteInsertInfo;

import java.util.List;

public interface RouteService {
    void createRoute(RouteInsertInfo routeInsertInfo);
    List<RouteInfo> findUserRoutes(int owner);
    void updateRoute(RouteInfo routeInfo);
    void deleteRoute(int owner, int routeSeq);
}
