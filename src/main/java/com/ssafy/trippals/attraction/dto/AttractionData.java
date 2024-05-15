package com.ssafy.trippals.attraction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AttractionData {
    private Integer contentId;
    private Integer contentTypeId;
    private String title;
    private String overview;
    private String addr;
    private String image;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
