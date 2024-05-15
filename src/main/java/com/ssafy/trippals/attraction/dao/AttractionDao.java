package com.ssafy.trippals.attraction.dao;

import com.ssafy.trippals.attraction.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttractionDao {
    List<RouteAttractionData> findByRouteSeq(int routeSeq);

    List<AttractionData> findByKeyword(AttractionKeywordSelect attractionKeywordSelect);
    List<AttractionData> findBySidoAndKeyword(AttractionSidoAndKeywordSelect attractionKeywordSelect);
    List<AttractionData> findByGugunAndKeyword(AttractionGugunAndKeywordSelect attractionKeywordSelect);

    int countByKeyword(AttractionKeywordSelect attractionKeywordSelect);
    int countBySidoAndKeyword(AttractionSidoAndKeywordSelect attractionKeywordSelect);
    int countByGugunAndKeyword(AttractionGugunAndKeywordSelect attractionKeywordSelect);

    List<AttractionData> findNearbyAttractionsByContentType(NearByAttractionContentTypeSelect attractionSelect);
    List<AttractionData> countNearbyAttractionsByContentType(NearByAttractionContentTypeSelect attractionSelect);
}
