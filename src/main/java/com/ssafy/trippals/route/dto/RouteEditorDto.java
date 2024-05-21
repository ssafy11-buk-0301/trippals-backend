package com.ssafy.trippals.route.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteEditorDto {
    private int seq;
    private int routeSeq;
    private int editor;

    public RouteEditorDto(int routeSeq, int editor) {
        this.routeSeq = routeSeq;
        this.editor = editor;
    }
}
