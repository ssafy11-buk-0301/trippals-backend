package com.ssafy.trippals.route.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RouteUpdate {
    private Integer seq;
    private Integer owner;
    private String name;
    private String overview;
    private String thumbnail;
}
