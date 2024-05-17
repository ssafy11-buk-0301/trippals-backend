package com.ssafy.trippals.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardInsert {
    private Integer userSeq;
    private String title;
    private String content;
    private boolean isDraft;//임시 여부 controller에서 결정
    private Integer routeSeq;


}
