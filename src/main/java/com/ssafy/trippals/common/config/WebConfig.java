//package com.ssafy.trippals.common.config;
//
//import com.ssafy.trippals.common.converter.IntegerToContentTypeConverter;
//import com.ssafy.trippals.common.interceptor.LogInterceptor;
//import com.ssafy.trippals.common.interceptor.LoginInterceptor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.format.FormatterRegistry;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    private final LoginInterceptor loginInterceptor;
//    private final LogInterceptor logInterceptor;
//    private final String imageUploadPath;
//
//    public WebConfig(LoginInterceptor loginInterceptor,
//                     LogInterceptor logInterceptor,
//                     @Value("${app.imageupload.upload.path}") String imageUploadPath) {
//        this.loginInterceptor = loginInterceptor;
//        this.logInterceptor = logInterceptor;
//        this.imageUploadPath = imageUploadPath;
//    }
//
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(new IntegerToContentTypeConverter());
//    }
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:5173", "http://localhost:5174")
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "TRACE")
//                .allowCredentials(true);
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/ws", "/login", "/signUp/**", "/attractions/**", "/docs/**", "/v3/**", "/swagger-ui/**", "**/resources/**", "/error", "/images/**", "/sidocode/**");
//
//        registry.addInterceptor(logInterceptor)
//                .addPathPatterns("/**");
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        String imagePath = "file:///" + imageUploadPath;
//        registry.addResourceHandler("/images/**")
//                .addResourceLocations(imagePath);
//    }
//}
