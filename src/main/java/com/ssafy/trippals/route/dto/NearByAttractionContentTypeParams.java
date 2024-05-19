package com.ssafy.trippals.route.dto;

import com.ssafy.trippals.common.page.dto.PageParams;
import lombok.Data;

@Data
public class NearByAttractionContentTypeParams {
    private PageParams pageParams;
    private Integer contentType;

    public NearByAttractionContentTypeParams(Integer limit, Integer offset, String orderBy, Integer contentType) {
        this.pageParams = new PageParams(offset, limit, orderBy);
        this.contentType = contentType;
    }
}
