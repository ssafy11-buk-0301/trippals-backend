package com.ssafy.trippals.common.converter;

import com.ssafy.trippals.attraction.dto.ContentType;
import org.springframework.core.convert.converter.Converter;

public class IntegerToContentTypeConverter implements Converter<Integer, ContentType> {
    @Override
    public ContentType convert(Integer source) {
        return ContentType.getContentType(source);
    }
}
