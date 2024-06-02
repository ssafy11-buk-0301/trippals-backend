//package com.ssafy.trippals.attraction.controller;
//
//import com.ssafy.trippals.attraction.dto.AttractionDto;
//import com.ssafy.trippals.attraction.dto.AttractionSearchParams;
//import com.ssafy.trippals.attraction.service.AttractionService;
//import com.ssafy.trippals.board.dto.BoardDto;
//import com.ssafy.trippals.common.page.dto.PageParams;
//import com.ssafy.trippals.common.page.dto.PageResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/attractions")
//public class AttractionController {
//    private final AttractionService attractionService;
//
//    @GetMapping
//    public ResponseEntity<PageResponse<AttractionDto>> searchAttractions(
//            @ModelAttribute AttractionSearchParams params
//    ) {
//        PageResponse<AttractionDto> contents =
//                    (params.getSidocode() == null) ? attractionService.findByKeyword(params) :
//                    (params.getGuguncode() == null) ? attractionService.findBySidoAndKeyword(params) :
//                     attractionService.findByGugunAndKeyword(params);
//
//        return ResponseEntity.ok(contents);
//    }
//
//    @GetMapping("/{contentId}/boards")
//    public ResponseEntity<PageResponse<BoardDto>> findReviews(
//            @PathVariable("contentId") int contentId,
//            @ModelAttribute PageParams params
//            ) {
//        return ResponseEntity.ok(attractionService.findReview(contentId, params));
//    }
//}
