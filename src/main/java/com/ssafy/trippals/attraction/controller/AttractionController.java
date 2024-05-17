package com.ssafy.trippals.attraction.controller;

import com.ssafy.trippals.attraction.dto.AttractionDto;
import com.ssafy.trippals.attraction.dto.AttractionSearchParams;
import com.ssafy.trippals.attraction.service.AttractionService;
import com.ssafy.trippals.common.page.dto.PageParams;
import com.ssafy.trippals.common.page.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attractions")
public class AttractionController {
    private final AttractionService attractionService;

    @GetMapping
    public ResponseEntity<PageResponse<AttractionDto>> searchAttractions(
            @ModelAttribute AttractionSearchParams params
    ) {
        PageParams pageParams = params.getPageParams();
        Integer sidocode = params.getSidocode();
        Integer guguncode = params.getGuguncode();
        String keyword = params.getKeyword();

        PageResponse<AttractionDto> contents =
                    (sidocode == null) ? attractionService.findByKeyword(pageParams, keyword) :
                    (guguncode == null) ? attractionService.findBySidoAndKeyword(pageParams, sidocode, keyword) :
                     attractionService.findByGugunAndKeyword(pageParams, sidocode, guguncode, keyword);

        return ResponseEntity.ok(contents);
    }
}
