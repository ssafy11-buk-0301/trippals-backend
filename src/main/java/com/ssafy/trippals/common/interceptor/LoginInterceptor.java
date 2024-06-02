package com.ssafy.trippals.common.interceptor;

import com.ssafy.trippals.common.SessionConst;
import com.ssafy.trippals.common.exception.UserAuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }
        log.debug("ipaddr: {}", request.getRequestURI());
        HttpSession session = request.getSession(false);
        if (session == null) throw new UserAuthException();

        Object user = session.getAttribute(SessionConst.USER);

        if (user == null) throw new UserAuthException();

        return true;
    }
}
