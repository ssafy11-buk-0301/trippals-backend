package com.ssafy.trippals.attraction.dto;

import com.ssafy.trippals.common.page.dto.PageParams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttractionSearchParams {
    private PageParams pageParams;
    private Integer sidocode;
    private Integer guguncode;
    private String keyword;
}
