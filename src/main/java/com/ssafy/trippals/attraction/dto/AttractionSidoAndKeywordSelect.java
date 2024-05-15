package com.ssafy.trippals.attraction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttractionSidoAndKeywordSelect {
    private int offset;
    private int limit;
    private int sidocode;
    private String keyword;
}
