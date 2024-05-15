package com.ssafy.trippals.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardParams {
    private Integer limit;
    private Integer offset;
    private String searchWord;
}
