package com.ssafy.trippals.common.page.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Function;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    private List<T> contents;
    private int page;
    private int size;
    private int totalPage;
    private int totalContents;

    public PageResponse(List<T> contents, int offset, int limit, int totalContents) {
        this.contents = contents;
        setPageAndSize(offset, limit, totalContents);
    }

    public PageResponse(List<T> contents, PageParams pageParams, int totalContents) {
        this.contents = contents;
        setPageAndSize(pageParams.getOffset(), pageParams.getLimit(), totalContents);
    }

    private void setPageAndSize(int offset, int limit, int totalContents) {
        this.page = (offset / limit) + 1;
        this.size = limit;
        this.totalContents = totalContents;
        this.totalPage = (int) Math.ceil((double) totalContents / limit);
    }
}
