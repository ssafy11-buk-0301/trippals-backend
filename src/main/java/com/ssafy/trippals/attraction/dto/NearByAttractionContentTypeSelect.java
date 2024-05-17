package com.ssafy.trippals.attraction.dto;

import com.ssafy.trippals.common.page.dto.PageParams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NearByAttractionContentTypeSelect {
    private int offset;
    private int limit;
    private int routeSeq;
    private int contentType;

    public NearByAttractionContentTypeSelect(int routeSeq, PageParams pageParams, ContentType contentType) {
        this.offset = pageParams.getOffset();
        this.limit = pageParams.getLimit();
        this.routeSeq = routeSeq;
        this.contentType = contentType.getCode();
    }
}
