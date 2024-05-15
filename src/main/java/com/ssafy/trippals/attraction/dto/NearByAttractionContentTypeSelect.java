package com.ssafy.trippals.attraction.dto;

import lombok.Data;

@Data
public class NearByAttractionContentTypeSelect {
    private int offset;
    private int limit;
    private int routeSeq;
    private int contentType;

    public NearByAttractionContentTypeSelect(int offset, int limit, int routeSeq, ContentType contentType) {
        this.offset = offset;
        this.limit = limit;
        this.routeSeq = routeSeq;
        this.contentType = contentType.getCode();
    }
}
