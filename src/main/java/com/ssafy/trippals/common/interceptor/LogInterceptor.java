package com.ssafy.trippals.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Enumeration;

@Component
@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("path: {}", request.getRequestURI());
        log.debug("method: {}", request.getMethod());
        log.debug("params: {}", collectionsToString(request.getParameterNames()));
        return true;
    }

    private String collectionsToString(Enumeration<String> collection) {
        StringBuilder sb = new StringBuilder();
        while (collection.hasMoreElements()) {
            sb.append(collection.nextElement());
            sb.append(",");
        }
        return sb.toString();
    }
}
