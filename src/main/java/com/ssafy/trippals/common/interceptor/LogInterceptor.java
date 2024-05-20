package com.ssafy.trippals.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Enumeration;
import java.util.Map;

@Component
@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("path: {}", request.getRequestURI());
        log.debug("method: {}", request.getMethod());
        log.debug("params: {}", collectionsToString(request.getParameterMap()));
        return true;
    }

    private String collectionsToString(Map<String, String[]> collection) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String[]> entry : collection.entrySet()) {
            sb.append(entry.getKey());
            sb.append(": ");
            for (String value : entry.getValue()) {
                sb.append(value);
                sb.append(", ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
