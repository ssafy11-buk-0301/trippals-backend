package com.ssafy.trippals.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private Integer seq;
    private Integer userSeq;
    private String title;
    private String content;
    private LocalDateTime regDt;
    private boolean isDraft;
    private Integer routeSeq;
}
