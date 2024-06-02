//package com.ssafy.trippals.route.controller;
//
//import com.ssafy.trippals.common.SessionConst;
//import com.ssafy.trippals.attraction.dto.AttractionDto;
//import com.ssafy.trippals.attraction.dto.ContentType;
//import com.ssafy.trippals.common.page.dto.PageResponse;
//import com.ssafy.trippals.event.EventService;
//import com.ssafy.trippals.event.EventType;
//import com.ssafy.trippals.route.dto.NearByAttractionContentTypeParams;
//import com.ssafy.trippals.route.service.RouteAttractionService;
//import com.ssafy.trippals.user.dto.UserDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/routes/{routeSeq}")
//@RequiredArgsConstructor
//public class RouteAttractionController {
//    private final RouteAttractionService routeAttractionService;
//    private final EventService eventService;
//
//    @GetMapping("/attractions")
//    public ResponseEntity<List<AttractionDto>> getRouteAttractions(
//            @SessionAttribute(SessionConst.USER) UserDto UserDto,
//            @PathVariable("routeSeq") int routeSeq
//    ) {
//        return ResponseEntity.ok(routeAttractionService.getRouteAttractions(UserDto.getSeq(), routeSeq));
//    }
//
//    @PostMapping("/attractions/{contentId}")
//    @ResponseStatus(HttpStatus.OK)
//    public void addRouteAttraction(
//            @SessionAttribute(SessionConst.USER) UserDto userDto,
//            @PathVariable("routeSeq") int routeSeq, @PathVariable("contentId") int contentId
//    ) {
//        routeAttractionService.addRouteAttraction(userDto.getSeq(), routeSeq, contentId);
//        eventService.sendRouteModifyEvent(routeSeq, EventType.UPDATE_ROUTE);
//        eventService.sendAiRecommendEvent(routeSeq, contentId);
//    }
//
//    @DeleteMapping("/attractions/{contentId}")
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteRouteAttraction(
//            @SessionAttribute(SessionConst.USER) UserDto UserDto,
//            @PathVariable("routeSeq") int routeSeq, @PathVariable("contentId") int contentId
//    ) {
//        routeAttractionService.deleteRouteAttraction(UserDto.getSeq(), routeSeq, contentId);
//        eventService.sendRouteModifyEvent(routeSeq, EventType.UPDATE_ROUTE);
//    }
//
//    @PutMapping("/attractions/{from}/{to}")
//    @ResponseStatus(HttpStatus.OK)
//    public void changeRouteAttractionOrder(
//            @SessionAttribute(SessionConst.USER) UserDto UserDto,
//            @PathVariable("routeSeq") int routeSeq, @PathVariable("from") int from, @PathVariable("to") int to
//    ) {
//        routeAttractionService.changeRouteAttraction(UserDto.getSeq(), routeSeq, from, to);
//        eventService.sendRouteModifyEvent(routeSeq, EventType.UPDATE_ROUTE);
//    }
//
//    @GetMapping("/nearby-attractions")
//    public ResponseEntity<PageResponse<AttractionDto>> getNearbyAttractions(
//            @SessionAttribute(SessionConst.USER) UserDto UserDto, @PathVariable("routeSeq") int routeSeq,
//            @ModelAttribute NearByAttractionContentTypeParams params
//    ) {
//        PageResponse<AttractionDto> pageResponse =
//                routeAttractionService.getNearbyRouteAttractions(UserDto.getSeq(), routeSeq,
//                        params.getPageParams(), ContentType.getContentType(params.getContentType()));
//
//        return ResponseEntity.ok(pageResponse);
//    }
//}
