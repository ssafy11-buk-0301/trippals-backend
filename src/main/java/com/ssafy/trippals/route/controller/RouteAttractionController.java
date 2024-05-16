package com.ssafy.trippals.route.controller;

import com.ssafy.trippals.SessionConst;
import com.ssafy.trippals.attraction.dto.AttractionInfoResponse;
import com.ssafy.trippals.common.page.dto.PageResponse;
import com.ssafy.trippals.route.service.RouteAttractionService;
import com.ssafy.trippals.user.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routes/{routeSeq}")
@RequiredArgsConstructor
public class RouteAttractionController {
    private final RouteAttractionService routeAttractionService;

    @GetMapping("/attractions")
    public ResponseEntity<List<AttractionInfoResponse>> getRouteAttractions(
            @SessionAttribute(SessionConst.USER) UserInfo userInfo,
            @PathVariable int routeSeq
    ) {
        List<AttractionInfoResponse> response =
                routeAttractionService.getRouteAttractions(userInfo.getSeq(), routeSeq).stream()
                .map(AttractionInfoResponse::new)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/attractions/{contentId}")
    @ResponseStatus(HttpStatus.OK)
    public void addRouteAttraction(
            @SessionAttribute(SessionConst.USER) UserInfo userInfo,
            @PathVariable int routeSeq, @PathVariable int contentId
    ) {
        routeAttractionService.addRouteAttraction(userInfo.getSeq(), routeSeq, contentId);
    }

    @DeleteMapping("/attractions/{contentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRouteAttraction(
            @SessionAttribute(SessionConst.USER) UserInfo userInfo,
            @PathVariable int routeSeq, @PathVariable int contentId
    ) {
        routeAttractionService.deleteRouteAttraction(userInfo.getSeq(), routeSeq, contentId);
    }

    @PutMapping("/attractions/{from}/{to}")
    @ResponseStatus(HttpStatus.OK)
    public void changeRouteAttractionOrder(
            @SessionAttribute(SessionConst.USER) UserInfo userInfo,
            @PathVariable int routeSeq, @PathVariable int from, @PathVariable int to
    ) {
        routeAttractionService.changeRouteAttraction(userInfo.getSeq(), routeSeq, from, to);
    }

    @GetMapping("/nearby-attractions")
    public ResponseEntity<PageResponse<AttractionInfoResponse>> getNearbyAttractions(
            @SessionAttribute(SessionConst.USER) UserInfo userInfo, @PathVariable int routeSeq
    ) {
        routeAttractionService.getNearbyRouteAttractions(userInfo.getSeq(), routeSeq);
    }
}
