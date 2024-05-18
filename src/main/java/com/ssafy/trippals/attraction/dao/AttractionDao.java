package com.ssafy.trippals.attraction.dao;

import com.ssafy.trippals.attraction.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AttractionDao {
    Optional<AttractionDto> findByContentId(int contentId);
    List<RouteAttractionDto> findByRouteSeq(int routeSeq);
    Optional<RouteAttractionDto> findByRouteSeqAndContentId(int routeSeq, int contentId);

    List<AttractionDto> findByKeyword(AttractionSearchParams attractionKeywordSelect);
    List<AttractionDto> findBySidoAndKeyword(AttractionSearchParams attractionKeywordSelect);
    List<AttractionDto> findByGugunAndKeyword(AttractionSearchParams attractionKeywordSelect);

    int countByKeyword(AttractionSearchParams attractionKeywordSelect);
    int countBySidoAndKeyword(AttractionSearchParams attractionKeywordSelect);
    int countByGugunAndKeyword(AttractionSearchParams attractionKeywordSelect);

    List<AttractionDto> findNearbyAttractionsByContentType(NearByAttractionContentTypeSelect attractionSelect);
    int countNearbyAttractionsByContentType(NearByAttractionContentTypeSelect attractionSelect);
}
