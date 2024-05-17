package com.ssafy.trippals.attraction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AttractionDto {
    private Integer contentId;
    private Integer contentTypeId;
    private String title;
    private String overview;
    private String addr;
    private String image;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public AttractionDto(RouteAttractionDto routeAttractionDto) {
        this.contentId = routeAttractionDto.getContentId();
        this.contentTypeId = routeAttractionDto.getContentTypeId();
        this.title = routeAttractionDto.getTitle();
        this.overview = routeAttractionDto.getOverview();
        this.addr = routeAttractionDto.getAddr();
        this.image = routeAttractionDto.getImage();
        this.latitude = routeAttractionDto.getLatitude();
        this.longitude = routeAttractionDto.getLongitude();
    }
}
