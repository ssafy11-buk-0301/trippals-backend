package com.ssafy.trippals.route.dto;

import com.ssafy.trippals.attraction.dto.ContentType;
import com.ssafy.trippals.common.page.dto.PageParams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NearByAttractionContentTypeParams {
    private PageParams pageParams;
    ContentType contentType;
}
