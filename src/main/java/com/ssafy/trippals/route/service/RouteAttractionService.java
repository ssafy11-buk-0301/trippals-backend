package com.ssafy.trippals.route.service;

import com.ssafy.trippals.common.page.dto.PageResponse;
import com.ssafy.trippals.route.dto.AttractionInfo;

import java.util.List;

public interface RouteAttractionService {
    List<AttractionInfo> getRouteAttractions(int userSeq, int routeSeq);
    void addRouteAttraction(int userSeq, int routeSeq, int contentId);
    void deleteRouteAttraction(int userSeq, int routeSeq, int contentId);
    void changeRouteAttraction(int userSeq, int routeSeq, int from, int to);
    PageResponse<AttractionInfo> getNearbyRouteAttractions(int userSeq, int routeSeq);
}
