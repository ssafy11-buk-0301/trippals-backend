package com.ssafy.trippals.route.service;

import com.ssafy.trippals.route.dto.RouteDto;

import java.util.List;

public interface RouteService {
    void createRoute(RouteDto routeInsertInfo);
    List<RouteDto> findUserRoutes(int owner);
    void updateRoute(RouteDto routeInfo);
    void deleteRoute(int owner, int routeSeq);
}
