package com.pible.config.sercurity.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pible.config.sercurity.model.PibleUser;
import com.pible.config.sercurity.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtUtils jwtUtils;
    private final ObjectMapper objectMapper;

    // 로그인 성공시 토큰을 발행합니다.
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        PibleUser pibleUser = (PibleUser) authentication.getPrincipal();
        Map<String, Object> claimMap = new HashMap<>();

        claimMap.put("nickName", pibleUser.getUserNickName());
        claimMap.put("authorities", pibleUser.getAuthorities());
        claimMap.put("if", pibleUser.getUserId());

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write(
                objectMapper.writeValueAsString(jwtUtils.createToken(pibleUser.getUsername(), claimMap))
        );
    }
}
