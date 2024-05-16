package com.ssafy.trippals.board.dto;

import lombok.Data;

@Data
public class BoardInsert {
    private Integer userSeq;
    private String title;
    private String content;
    private boolean isDraft;
    private Integer routeSeq;
}
