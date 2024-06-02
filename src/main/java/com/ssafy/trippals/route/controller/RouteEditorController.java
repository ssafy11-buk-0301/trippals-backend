//package com.ssafy.trippals.route.controller;
//
//import com.ssafy.trippals.common.SessionConst;
//import com.ssafy.trippals.common.page.dto.PageParams;
//import com.ssafy.trippals.common.page.dto.PageResponse;
//import com.ssafy.trippals.event.EventService;
//import com.ssafy.trippals.event.EventType;
//import com.ssafy.trippals.route.dto.RouteDto;
//import com.ssafy.trippals.route.dto.RouteEditorRequestDto;
//import com.ssafy.trippals.route.service.RouteEditorService;
//import com.ssafy.trippals.user.dto.UserDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class RouteEditorController {
//    private final RouteEditorService routeEditorService;
//    private final EventService eventService;
//
//    @GetMapping("/routes/{routeSeq}/editors")
//    public ResponseEntity<List<UserDto>> getRouteEditors(
//            @SessionAttribute(SessionConst.USER) UserDto userDto,
//            @PathVariable("routeSeq") int routeSeq
//    ) {
//        return ResponseEntity.ok(routeEditorService.findAllEditors(userDto.getSeq(), routeSeq));
//    }
//
//    @DeleteMapping("/routes/{routeSeq}/editors")
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteRouteEditor(
//            @SessionAttribute(SessionConst.USER) UserDto userDto,
//            @PathVariable("routeSeq") int routeSeq,
//            @RequestParam("editorSeq") Integer editorSeq
//    ) {
//        routeEditorService.removeEditor(routeSeq, userDto.getSeq(), editorSeq);
//    }
//
//    @GetMapping("/editors/routes")
//    public ResponseEntity<PageResponse<RouteDto>> getEditableRoute(
//            @SessionAttribute(SessionConst.USER) UserDto userDto,
//            @ModelAttribute PageParams pageParams
//    ) {
//        return ResponseEntity.ok(routeEditorService.findAllEditableRoutes(userDto.getSeq(), pageParams));
//    }
//
//    @GetMapping("/editors/requests")
//    public ResponseEntity<List<RouteEditorRequestDto>> getRequest(
//            @SessionAttribute(SessionConst.USER) UserDto userDto
//    ) {
//        return ResponseEntity.ok(routeEditorService.findAllRequests(userDto.getSeq()));
//    }
//
//    @PostMapping("/routes/{routeSeq}/editors")
//    @ResponseStatus(HttpStatus.OK)
//    public void addRouteEditorRequest(
//            @SessionAttribute(SessionConst.USER) UserDto userDto,
//            @PathVariable("routeSeq") int routeSeq,
//            @RequestParam("editor") String editor
//    ) {
//        routeEditorService.addRequest(routeSeq, userDto.getSeq(), editor);
//    }
//
//    @PostMapping("/routes/{routeSeq}/editors/confirm")
//    @ResponseStatus(HttpStatus.OK)
//    public void confirmRouteEditorRequest(
//            @SessionAttribute(SessionConst.USER) UserDto userDto,
//            @PathVariable("routeSeq") int routeSeq
//    ) {
//        routeEditorService.confirmRequest(routeSeq, userDto.getSeq());
//        eventService.sendRouteModifyEvent(routeSeq, EventType.ADD_EDITOR);
//    }
//
//    @PostMapping("/routes/{routeSeq}/editors/reject")
//    @ResponseStatus(HttpStatus.OK)
//    public void rejectRouteEditorRequest(
//            @SessionAttribute(SessionConst.USER) UserDto userDto,
//            @PathVariable("routeSeq") int routeSeq
//    ) {
//        routeEditorService.rejectRequest(routeSeq, userDto.getSeq());
//    }
//}
