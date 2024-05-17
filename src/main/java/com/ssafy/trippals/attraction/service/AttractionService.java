package com.ssafy.trippals.attraction.service;

import com.ssafy.trippals.attraction.dto.AttractionDto;
import com.ssafy.trippals.common.page.dto.PageParams;
import com.ssafy.trippals.common.page.dto.PageResponse;

import java.util.List;

public interface AttractionService {
    PageResponse<AttractionDto> findByGugunAndKeyword(PageParams pageParams, int sidocode, int guguncode, String keyword);
    PageResponse<AttractionDto> findBySidoAndKeyword(PageParams pageParams, int sidocode, String keyword);
    PageResponse<AttractionDto> findByKeyword(PageParams pageParams, String keyword);
}
