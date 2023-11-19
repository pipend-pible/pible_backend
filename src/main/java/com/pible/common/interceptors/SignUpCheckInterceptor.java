package com.pible.common.interceptors;

import com.pible.common.Constants;
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

    // 회원가입시 무분별한 회원 가입을 막기 위한 작업입니다.
    // 회원 가입을 하는 동안 인증용 토큰을 발행하고 SignUpChecker 어노테이션이 걸려있는 컨트롤러로 요청이 올 경우 검증을 시도합니다.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(getAnnotation((HandlerMethod) handler) == null) {
            return true;
        }

        String jwt = jwtUtils.getJwtFromRequest(request);

        if(!jwtUtils.validateToken(jwt)) {
            throw new BusinessException(ResponseCode.NOT_EXIST_TOKEN);
        }

        // jwt 서명 검증 이후 jwt 의 subject 값인 유저네임(이메일) 과 회원 가입 관련 요청의 파라미터로 들어온 유저네임을 비교하여 추가 검증합니다.
        request.setAttribute(Constants.USER_NAME, jwtUtils.getData(jwt).getSubject());

        return true;
    }

    private SignUpChecker getAnnotation(HandlerMethod handlerMethod) {
        return handlerMethod.getMethodAnnotation(SignUpChecker.class);
    }
}
