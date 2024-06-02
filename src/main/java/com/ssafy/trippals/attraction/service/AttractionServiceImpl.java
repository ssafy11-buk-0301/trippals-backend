//package com.ssafy.trippals.attraction.service;
//
//import com.ssafy.trippals.attraction.dao.AttractionDao;
//import com.ssafy.trippals.attraction.dto.AttractionDto;
//import com.ssafy.trippals.attraction.dto.AttractionSearchParams;
//import com.ssafy.trippals.board.dto.BoardDto;
//import com.ssafy.trippals.common.page.dto.PageParams;
//import com.ssafy.trippals.common.page.dto.PageResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class AttractionServiceImpl implements AttractionService {
//    private final AttractionDao attractionDao;
//
//    @Override
//    public PageResponse<AttractionDto> findByGugunAndKeyword(AttractionSearchParams params) {
//        List<AttractionDto> contents = attractionDao.findByGugunAndKeyword(params);
//        int allContentsSize = attractionDao.countByGugunAndKeyword(params);
//
//        return new PageResponse<>(contents, params.getPageParams(), allContentsSize);
//    }
//
//    @Override
//    public PageResponse<AttractionDto> findBySidoAndKeyword(AttractionSearchParams params) {
//        List<AttractionDto> contents = attractionDao.findBySidoAndKeyword(params);
//        int allContentsSize = attractionDao.countBySidoAndKeyword(params);
//
//        return new PageResponse<>(contents, params.getPageParams(), allContentsSize);
//    }
//
//    @Override
//    public PageResponse<AttractionDto> findByKeyword(AttractionSearchParams params) {
//        List<AttractionDto> contents = attractionDao.findByKeyword(params);
//        int allContentsSize = attractionDao.countByKeyword(params);
//
//        return new PageResponse<>(contents, params.getPageParams(), allContentsSize);
//    }
//
//    @Override
//    public PageResponse<BoardDto> findReview(int contentId, PageParams params) {
//        List<BoardDto> contents = attractionDao.findReviewByContentId(contentId, params.getLimit(), params.getOffset());
//        int allContentSize = attractionDao.countReviewByContentId(contentId);
//
//        return new PageResponse<>(contents, params, allContentSize);
//    }
//}
