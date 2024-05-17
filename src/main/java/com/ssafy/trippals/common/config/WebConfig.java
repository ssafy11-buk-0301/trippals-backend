package com.ssafy.trippals.common.config;

import com.ssafy.trippals.common.converter.IntegerToContentTypeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new IntegerToContentTypeConverter());
    }
}
