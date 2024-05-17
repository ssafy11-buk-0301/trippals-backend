package com.ssafy.trippals.attraction.service;

import com.ssafy.trippals.attraction.dao.AttractionDao;
import com.ssafy.trippals.attraction.dto.AttractionDto;
import com.ssafy.trippals.attraction.dto.AttractionGugunAndKeywordSelect;
import com.ssafy.trippals.common.page.dto.PageParams;
import com.ssafy.trippals.common.page.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {
    private final AttractionDao attractionDao;

    @Override
    public PageResponse<AttractionDto> findByGugunAndKeyword(PageParams pageParams, int sidocode, int guguncode, String keyword) {
        attractionDao.findByGugunAndKeyword(new AttractionGugunAndKeywordSelect())
        return null;
    }

    @Override
    public PageResponse<AttractionDto> findBySidoAndKeyword(PageParams pageParams, int sidocode, String keyword) {
        return null;
    }

    @Override
    public PageResponse<AttractionDto> findByKeyword(PageParams pageParams, String keyword) {
        return null;
    }
}
