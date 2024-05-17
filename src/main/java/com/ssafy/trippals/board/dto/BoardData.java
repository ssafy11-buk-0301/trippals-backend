package com.ssafy.trippals.board.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardData {
    private Integer seq;
    private Integer userSeq;
    private String title;
    private String content;
    private LocalDateTime regDt;
    private boolean isDraft;
    private Integer routeSeq;
}
