package com.ssafy.trippals.route.controller;

import com.ssafy.trippals.SessionConst;
import com.ssafy.trippals.route.dto.*;
import com.ssafy.trippals.route.service.RouteService;
import com.ssafy.trippals.user.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routes")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createRoute(
            @SessionAttribute(SessionConst.USER) UserInfo userInfo,
            @ModelAttribute RouteInsertForm routeInsertForm
    ) {
        routeService.createRoute(new RouteInsertInfo(routeInsertForm, userInfo.getSeq()));
    }

    @GetMapping
    public ResponseEntity<List<RouteInfoResponse>> getRoutes(
            @SessionAttribute(SessionConst.USER) UserInfo userInfo
    ) {
        List<RouteInfoResponse> userRouteInfoResponses =
                routeService.findUserRoutes(userInfo.getSeq()).stream()
                        .map(RouteInfoResponse::new)
                        .toList();

        return ResponseEntity.ok(userRouteInfoResponses);
    }

    @PutMapping("/{routeSeq}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRoute(
            @SessionAttribute(SessionConst.USER) UserInfo userInfo,
            @PathVariable Integer routeSeq,
            @ModelAttribute RouteUpdateForm routeUpdateForm
    ) {
        RouteInfo routeInfo = new RouteInfo(routeSeq, userInfo.getSeq(), routeUpdateForm.getName(),
                routeUpdateForm.getOverview(), routeUpdateForm.getThumbnail(), routeUpdateForm.getStartDate());

        routeService.updateRoute(routeInfo);
    }

    @DeleteMapping("/{routeSeq}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRoute(
            @SessionAttribute(SessionConst.USER) UserInfo userInfo,
            @PathVariable Integer routeSeq
    ) {
        routeService.deleteRoute(userInfo.getSeq(), routeSeq);
    }
}
