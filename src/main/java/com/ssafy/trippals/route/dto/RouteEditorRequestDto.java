package com.ssafy.trippals.route.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteEditorRequestDto {
    private Integer seq;
    private Integer routeSeq;
    private String routeName;
    private Integer userSeq;

    public RouteEditorRequestDto(Integer routeSeq, String routeName, Integer userSeq) {
        this.routeSeq = routeSeq;
        this.routeName = routeName;
        this.userSeq = userSeq;
    }
}
