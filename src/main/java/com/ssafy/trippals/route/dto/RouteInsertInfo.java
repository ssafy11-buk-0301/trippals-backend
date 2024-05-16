package com.ssafy.trippals.route.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteInsertInfo {
    private Integer owner;
    private String name;
    private String overview;
    private String thumbnail;
    private LocalDateTime startDate;

    public RouteInsertInfo(RouteInsertForm routeInsertForm, Integer owner) {
        this.owner = owner;
        this.name = routeInsertForm.getName();
        this.overview = routeInsertForm.getOverview();
        this.thumbnail = routeInsertForm.getThumbnail();
        this.startDate = routeInsertForm.getStartDate();
    }
}
