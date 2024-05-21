package com.ssafy.trippals.route.controller;

import com.ssafy.trippals.SessionConst;
import com.ssafy.trippals.common.page.dto.PageParams;
import com.ssafy.trippals.common.page.dto.PageResponse;
import com.ssafy.trippals.route.dto.RouteDto;
import com.ssafy.trippals.route.service.RouteEditorService;
import com.ssafy.trippals.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RouteEditorController {
    private final RouteEditorService routeEditorService;

    @GetMapping("/routes/{routeSeq}/editors")
    public ResponseEntity<List<UserDto>> getRouteEditors(
            @SessionAttribute(SessionConst.USER) UserDto userDto,
            @PathVariable("routeSeq") int routeSeq
    ) {
        return ResponseEntity.ok(routeEditorService.findAllEditors(userDto.getSeq(), routeSeq));
    }

    @PostMapping("/routes/{routeSeq}/editors")
    @ResponseStatus(HttpStatus.OK)
    public void addRouteEditor(
            @SessionAttribute(SessionConst.USER) UserDto userDto,
            @PathVariable("routeSeq") int routeSeq,
            @RequestParam("editorSeq") Integer editorSeq
    ) {
        routeEditorService.addEditor(routeSeq, userDto.getSeq(), editorSeq);
    }

    @DeleteMapping("/routes/{routeSeq}/editors")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRouteEditor(
            @SessionAttribute(SessionConst.USER) UserDto userDto,
            @PathVariable("routeSeq") int routeSeq,
            @RequestParam("editorSeq") Integer editorSeq
    ) {
        routeEditorService.removeEditor(routeSeq, userDto.getSeq(), editorSeq);
    }

    @GetMapping("/editors/routes")
    public ResponseEntity<PageResponse<RouteDto>> getEditableRoute(
            @SessionAttribute(SessionConst.USER) UserDto userDto,
            @ModelAttribute PageParams pageParams
    ) {
        return ResponseEntity.ok(routeEditorService.findAllEditableRoutes(userDto.getSeq(), pageParams));
    }
}
