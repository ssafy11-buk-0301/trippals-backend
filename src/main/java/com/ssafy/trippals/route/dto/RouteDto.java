package com.ssafy.trippals.route.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteDto {
    private Integer seq;
    private Integer owner;
    private String name;
    private String overview;
    private String thumbnail;
    private LocalDate startDate;

    public RouteDto(Integer owner, String name, String overview, String thumbnail, LocalDate startDate) {
        this.owner = owner;
        this.name = name;
        this.overview = overview;
        this.thumbnail = thumbnail;
        this.startDate = startDate;
    }
}
