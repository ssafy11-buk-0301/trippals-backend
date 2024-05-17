package com.ssafy.trippals.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardParams {
    private Integer limit;
    private Integer offset;
    private String searchWord;
    private String orderBy;
}
