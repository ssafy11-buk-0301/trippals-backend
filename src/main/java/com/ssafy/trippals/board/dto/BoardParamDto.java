package com.ssafy.trippals.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardParamDto {
    private Integer limit;
    private Integer offset;
    private String searchWord;
    private String orderBy;
    private String ascDesc;

    private Integer boardSeq;
    private Integer userSeq;
    public BoardParamDto(Integer limit,Integer offset,String searchWord,String orderBy){
        this.limit=limit;
        this.offset=offset;
        this.searchWord=searchWord;
        this.orderBy=orderBy;
    };

}
