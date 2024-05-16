package com.ssafy.trippals.route.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RouteInfoResponse {
    private Integer seq;
    private String name;
    private String overview;
    private String thumbnail;
    private LocalDateTime startDate;

    public RouteInfoResponse(RouteInfo routeInfo) {
        this.seq = routeInfo.getSeq();
        this.name = routeInfo.getName();
        this.overview = routeInfo.getOverview();
        this.thumbnail = routeInfo.getThumbnail();
        this.startDate = routeInfo.getStartDate();
    }
}
