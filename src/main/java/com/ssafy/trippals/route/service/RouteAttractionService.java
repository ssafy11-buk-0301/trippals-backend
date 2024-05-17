package com.ssafy.trippals.route.service;

import com.ssafy.trippals.attraction.dto.AttractionDto;
import com.ssafy.trippals.attraction.dto.ContentType;
import com.ssafy.trippals.common.page.dto.PageParams;
import com.ssafy.trippals.common.page.dto.PageResponse;

import java.util.List;

public interface RouteAttractionService {
    List<AttractionDto> getRouteAttractions(int userSeq, int routeSeq);
    void addRouteAttraction(int userSeq, int routeSeq, int contentId);
    void deleteRouteAttraction(int userSeq, int routeSeq, int contentId);
    void changeRouteAttraction(int userSeq, int routeSeq, int from, int to);
    PageResponse<AttractionDto> getNearbyRouteAttractions(int userSeq, int routeSeq, PageParams pageParams, ContentType contentType);
}
