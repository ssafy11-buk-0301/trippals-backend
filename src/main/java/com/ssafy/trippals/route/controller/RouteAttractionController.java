package com.ssafy.trippals.route.controller;

import com.ssafy.trippals.SessionConst;
import com.ssafy.trippals.attraction.dto.AttractionInfoResponse;
import com.ssafy.trippals.common.page.dto.PageResponse;
import com.ssafy.trippals.user.dto.UserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routes/{routeSeq}")
public class RouteAttractionController {
    @GetMapping("/attractions")
    public ResponseEntity<List<AttractionInfoResponse>> getRouteAttractions(
            @SessionAttribute(SessionConst.USER) UserInfo userInfo,
            @PathVariable int routeSeq
    ) {

    }

    @PostMapping("/attractions/{contentId}")
    @ResponseStatus(HttpStatus.OK)
    public void addRouteAttraction(
            @SessionAttribute(SessionConst.USER) UserInfo userInfo,
            @PathVariable int routeSeq, @PathVariable int contentId
    ) {

    }

    @DeleteMapping("/attractions/{contentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRouteAttraction(
            @SessionAttribute(SessionConst.USER) UserInfo userInfo,
            @PathVariable int routeSeq, @PathVariable int contentId
    ) {

    }

    @PutMapping("/attractions/{from}/{to}")
    @ResponseStatus(HttpStatus.OK)
    public void changeRouteAttractionOrder(
            @SessionAttribute(SessionConst.USER) UserInfo userInfo,
            @PathVariable int from, @PathVariable int to
    ) {

    }

    @GetMapping("/nearby-attractions")
    public ResponseEntity<PageResponse<AttractionInfoResponse>> getNearbyAttractions(

    ) {
        
    }
}
