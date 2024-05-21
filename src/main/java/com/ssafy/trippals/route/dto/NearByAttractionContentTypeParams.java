package com.ssafy.trippals.route.dto;

import com.ssafy.trippals.common.page.dto.PageParams;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NearByAttractionContentTypeParams {
    private PageParams pageParams;
    private Integer contentType;

    public NearByAttractionContentTypeParams(Integer limit, Integer offset, Integer contentType) {
        this.pageParams = new PageParams(offset, limit, null);
        this.contentType = contentType;
    }
}
