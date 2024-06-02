//package com.ssafy.trippals.route.controller;
//
//import com.ssafy.trippals.common.SessionConst;
//import com.ssafy.trippals.route.dto.*;
//import com.ssafy.trippals.route.service.RouteService;
//import com.ssafy.trippals.user.dto.UserDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/routes")
//@RequiredArgsConstructor
//public class RouteController {
//    private final RouteService routeService;
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.OK)
//    public void createRoute(
//            @SessionAttribute(SessionConst.USER) UserDto UserDto,
//            @ModelAttribute RouteForm routeForm
//    ) {
//        routeService.createRoute(UserDto.getSeq(), routeForm);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<RouteDto>> getRoutes(
//            @SessionAttribute(SessionConst.USER) UserDto UserDto
//    ) {
//        List<RouteDto> userRouteInfoResponses =
//                routeService.findUserRoutes(UserDto.getSeq()).stream()
//                        .toList();
//
//        return ResponseEntity.ok(userRouteInfoResponses);
//    }
//
//    @PostMapping("/{routeSeq}")
//    @ResponseStatus(HttpStatus.OK)
//    public void updateRoute(
//            @SessionAttribute(SessionConst.USER) UserDto userDto,
//            @PathVariable("routeSeq") Integer routeSeq,
//            @ModelAttribute RouteForm routeForm
//    ) {
//        routeService.updateRoute(routeSeq, userDto.getSeq(), routeForm);
//    }
//
//    @DeleteMapping("/{routeSeq}")
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteRoute(
//            @SessionAttribute(SessionConst.USER) UserDto UserDto,
//            @PathVariable("routeSeq") Integer routeSeq
//    ) {
//        routeService.deleteRoute(UserDto.getSeq(), routeSeq);
//    }
//}
