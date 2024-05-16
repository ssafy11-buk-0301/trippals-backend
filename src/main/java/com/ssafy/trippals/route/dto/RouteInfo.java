package com.ssafy.trippals.route.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteInfo {
    private Integer seq;
    private Integer owner;
    private String name;
    private String overview;
    private String thumbnail;
    private LocalDateTime startDate;
}
