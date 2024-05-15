package com.ssafy.trippals.route.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RouteInsert {
    private Integer seq;
    private Integer owner;
    private String name;
    private String overview;
    private String thumbnail;

    public RouteInsert(Integer owner, String name, String overview, String thumbnail) {
        this.owner = owner;
        this.name = name;
        this.overview = overview;
        this.thumbnail = thumbnail;
    }
}
