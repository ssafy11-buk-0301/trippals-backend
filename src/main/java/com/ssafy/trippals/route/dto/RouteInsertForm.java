package com.ssafy.trippals.route.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RouteInsertForm {
    private String name;
    private String overview;
    private String thumbnail;
    private LocalDateTime startDate;
}
