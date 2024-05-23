package com.ssafy.trippals.attraction.service;

import com.ssafy.trippals.attraction.dto.AttractionDto;
import com.ssafy.trippals.attraction.dto.AttractionSearchParams;
import com.ssafy.trippals.board.dto.BoardDto;
import com.ssafy.trippals.common.page.dto.PageParams;
import com.ssafy.trippals.common.page.dto.PageResponse;

public interface AttractionService {
    PageResponse<AttractionDto> findByGugunAndKeyword(AttractionSearchParams params);
    PageResponse<AttractionDto> findBySidoAndKeyword(AttractionSearchParams params);
    PageResponse<AttractionDto> findByKeyword(AttractionSearchParams params);
    PageResponse<BoardDto> findReview(int contentId, PageParams params);
}
