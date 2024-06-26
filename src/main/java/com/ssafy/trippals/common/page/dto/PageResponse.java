package com.ssafy.trippals.common.page.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
        setPageAndSize(offset, limit, totalContents, Math.min(contents.size(), limit));
    }

    public PageResponse(List<T> contents, PageParams pageParams, int totalContents) {
        this.contents = contents;
        setPageAndSize(pageParams.getOffset(), pageParams.getLimit(), totalContents, Math.min(contents.size(), pageParams.getLimit()));
    }

    private void setPageAndSize(int offset, int limit, int totalContents, int size) {
        this.page = limit <= 0 ? 1 : (offset / limit) + 1;
        this.totalContents = totalContents;
        this.size = size;
        this.totalPage = limit <= 0 || totalContents == 0 ? 1 : (int) Math.ceil((double) totalContents / limit);
    }
}
