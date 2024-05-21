package com.ssafy.trippals.route.service;

import com.ssafy.trippals.common.page.dto.PageParams;
import com.ssafy.trippals.common.page.dto.PageResponse;
import com.ssafy.trippals.route.dto.RouteDto;
import com.ssafy.trippals.user.dto.UserDto;

import java.util.List;

public interface RouteEditorService {
    List<UserDto> findAllEditors(int userSeq, int routeSeq);
    PageResponse<RouteDto> findAllEditableRoutes(int userSeq, PageParams pageParams);
    boolean addEditor(int routeSeq, int owner, int editor);
    boolean removeEditor(int routeSeq, int owner, int editor);
    boolean canEdit(int routeSeq, int userSeq);
    boolean isEditor(int routeSeq, int userSeq);
    boolean isOwner(int routeSeq, int userSeq);
}
