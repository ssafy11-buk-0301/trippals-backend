package com.ssafy.trippals.attraction.dto;

import com.ssafy.trippals.common.page.dto.PageParams;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttractionSearchParams {
    private PageParams pageParams;
    private Integer sidocode;
    private Integer guguncode;
    private String keyword;

    public AttractionSearchParams() {
        pageParams = new PageParams();
    }

    public AttractionSearchParams(PageParams pageParams, String keyword, Integer sidocode)  {
        this.pageParams = pageParams;
        this.sidocode = sidocode;
        this.keyword = keyword;
    }

    public AttractionSearchParams(PageParams pageParams, String keyword, Integer sidocode, Integer guguncode)  {
        this.pageParams = pageParams;
        this.sidocode = sidocode;
        this.keyword = keyword;
    }

    public AttractionSearchParams(PageParams pageParams, String keyword) {
        this.pageParams = pageParams;
        this.keyword = keyword;
    }

    public void setOffset(int offset) {
        pageParams.setOffset(offset);
    }

    public void setLimit(int limit) {
        pageParams.setLimit(limit);
    }

    public void setOrderBy(String orderBy) {
        pageParams.setOrderBy(orderBy);
    }
}
