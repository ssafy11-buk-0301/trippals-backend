package com.ssafy.trippals.route.dao;

import com.ssafy.trippals.route.dto.RouteDto;
import com.ssafy.trippals.route.dto.RouteEditorDto;
import com.ssafy.trippals.route.dto.RouteEditorRequestDto;
import com.ssafy.trippals.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RouteEditorDao {
    List<RouteEditorRequestDto> findRequestByUserSeq(@Param("userSeq") int userSeq);
    int insertRouteEditorRequest(RouteEditorRequestDto routeEditorRequestDto);
    int deleteRouteEditorRequest(@Param("seq") int seq);

    List<RouteDto> findRouteByEditorSeq(@Param("userSeq") int userSeq, @Param("offset") int offset, @Param("limit") int limit);
    int countRouteByEditorSeq(@Param("userSeq") int userSeq);

    List<UserDto> findEditorByRouteSeq(@Param("routeSeq") int routeSeq);

    int insertRouteEditor(RouteEditorDto routeEditorDto);
    int deleteRouteEditor(@Param("seq") int seq);
}
