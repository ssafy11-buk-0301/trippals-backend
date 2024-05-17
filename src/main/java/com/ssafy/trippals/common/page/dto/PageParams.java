package com.ssafy.trippals.common.page.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParams {
    private int offset = 0;
    private int limit = 10;
    private String orderBy;
}
