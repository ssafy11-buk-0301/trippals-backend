package com.ssafy.trippals.attraction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttractionGugunAndKeywordSelect {
    private int offset;
    private int limit;
    private int sidocode;
    private int guguncode;
    private String keyword;
}
