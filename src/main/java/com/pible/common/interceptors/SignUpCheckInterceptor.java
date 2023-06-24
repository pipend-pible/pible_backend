package com.pible.common.interceptors;

import com.pible.common.annotations.SignUpChecker;
import com.pible.common.enums.ResponseCode;
import com.pible.common.exception.BusinessException;
import com.pible.config.sercurity.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class SignUpCheckInterceptor implements HandlerInterceptor {
    private final JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(getAnnotation((HandlerMethod) handler) == null) {
            return true;
        }

        String jwt = jwtUtils.getJwtFromRequest(request);

        if(!jwtUtils.validateToken(jwt)) {
            throw new BusinessException(ResponseCode.FAIL);
        }

        request.setAttribute("userName", jwtUtils.getData(jwt).getSubject());

        return true;
    }

    private SignUpChecker getAnnotation(HandlerMethod handlerMethod) {
        return handlerMethod.getMethodAnnotation(SignUpChecker.class);
    }
}
