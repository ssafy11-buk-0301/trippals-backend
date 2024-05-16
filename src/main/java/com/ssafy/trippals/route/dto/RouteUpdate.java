package com.ssafy.trippals.route.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RouteUpdate {
    private Integer seq;
    private Integer owner;
    private String name;
    private String overview;
    private String thumbnail;
    private LocalDateTime startDate;

    public RouteUpdate(RouteInfo routeInfo) {
        this.seq = routeInfo.getSeq();
        this.owner = routeInfo.getOwner();
        this.name = routeInfo.getName();
        this.overview = routeInfo.getOverview();
        this.thumbnail = routeInfo.getThumbnail();
        this.startDate = routeInfo.getStartDate();
    }
}
