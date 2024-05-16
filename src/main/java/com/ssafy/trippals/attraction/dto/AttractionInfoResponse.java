package com.ssafy.trippals.attraction.dto;

import com.ssafy.trippals.route.dto.AttractionInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttractionInfoResponse {
    private Integer contentId;
    private Integer contentTypeId;
    private String title;
    private String overview;
    private String addr;
    private String image;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public AttractionInfoResponse(AttractionInfo attractionInfo) {
        this.contentId = attractionInfo.getContentId();
        this.contentTypeId = attractionInfo.getContentTypeId();
        this.title = attractionInfo.getTitle();
        this.overview = attractionInfo.getOverview();
        this.addr = attractionInfo.getAddr();
        this.image = attractionInfo.getImage();
        this.latitude = attractionInfo.getLatitude();
        this.longitude = attractionInfo.getLongitude();
    }
}
