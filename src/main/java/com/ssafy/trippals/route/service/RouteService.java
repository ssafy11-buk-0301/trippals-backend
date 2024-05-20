package com.ssafy.trippals.route.service;

import com.ssafy.trippals.route.dto.RouteDto;
import com.ssafy.trippals.route.dto.RouteForm;

import java.util.List;

public interface RouteService {
    void createRoute(int owner, RouteForm routeForm);
    List<RouteDto> findUserRoutes(int owner);
    void updateRoute(int seq, int owner, RouteForm routeForm);
    void deleteRoute(int owner, int routeSeq);
}
