package com.ssafy.trippals.route.dao;

import com.ssafy.trippals.attraction.dto.RouteAttractionDto;
import com.ssafy.trippals.route.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RouteDao {
    List<RouteDto> findRouteDtoByOwner(Integer owner);
    Optional<RouteDto> findRouteDtoBySeq(Integer seq);
    Optional<RouteAttractionDto> findRouteAttractionDtoByRouteSeqAndContentId(@Param("routeSeq") Integer routeSeq, @Param("contentId") Integer contentId);
    int insertRoute(RouteDto routeDto);
    int updateRoute(RouteDto routeDto);
    int deleteRouteBySeq(Integer seq);

    int insertAttractionIntoRoute(@Param("routeSeq") Integer routeSeq, @Param("attractionSeq") Integer attractionSeq);
    int deleteAttractionFromRoute(@Param("routeSeq") Integer routeSeq, @Param("attractionSeq") Integer attractionSeq);
    int updateRouteAttractionOrder(@Param("routeSeq") Integer routeSeq, @Param("attractionSeq") Integer attractionSeq, @Param("order") Integer order);
}
