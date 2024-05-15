package com.ssafy.trippals.attraction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttractionKeywordSelect {
    private int offset;
    private int limit;
    private String keyword;
}
