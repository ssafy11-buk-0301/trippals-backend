package com.ssafy.trippals.route.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RouteData {
    private Integer seq;
    private Integer owner;
    private String name;
    private String overview;
    private String thumbnail;
    private LocalDateTime startDate;
}
