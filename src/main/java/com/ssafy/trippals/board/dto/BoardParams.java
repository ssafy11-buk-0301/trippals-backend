package com.ssafy.trippals.board.dto;

import lombok.Data;

@Data
public class BoardParams {
    private Integer limit;
    private Integer offset;
    private String searchWord;
}
