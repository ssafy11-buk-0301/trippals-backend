package com.ssafy.trippals.route.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RouteAttractionOrderUpdate {
    private Integer routeSeq;
    private Integer attractionSeq;
    private Integer order;
}
