package com.pible.config.sercurity.filters;

import com.pible.config.sercurity.model.PibleUser;
import com.pible.config.sercurity.model.UserAuthenticationToken;
import com.pible.config.sercurity.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
// 인증 방식으로 jwt 를 선택했기 때문에 매 요청마다 토큰을 검증합니다.
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final String[] ignored;
    public JwtAuthenticationFilter(JwtUtils jwtUtils, String[] ignored) {
        this.jwtUtils = jwtUtils;
        this.ignored = ignored;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if(HttpMethod.GET.matches(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String jwt = jwtUtils.getJwtFromRequest(request);
            if(!jwtUtils.validateToken(jwt)) {
                response.sendError(HttpStatus.SC_UNAUTHORIZED);
                return;
            }

            // 토큰 데이터 추출을 위한 로직입니다. 토큰을 발행할 때와 달리
            // 토큰에서 데이터를 추출할때 List<LinkedHashMap<String, String>> 타입으로 추출되어 추가적인 처리를 했습니다.
            // 불필요한 작업일 경우 개선이 필요합니다.
            Claims claims = jwtUtils.getData(jwt);
            String nickName = String.valueOf(claims.get("nickName"));
            Long userId = Long.parseLong(String.valueOf(claims.get("if")));
            List<LinkedHashMap<String, String>> linkedAuthorities = (List<LinkedHashMap<String, String>>) claims.get("authorities");
            Set<GrantedAuthority> authorities = linkedAuthorities.stream().flatMap(linkedHashMap -> linkedHashMap.values().stream().map(SimpleGrantedAuthority::new)).collect(Collectors.toSet());

            // 토큰 검증 후 토큰의 데이터를 이용하여 authentication 정보를 할당합니다.
            PibleUser pibleUser = new PibleUser(claims.getSubject(), "temporal", authorities, nickName, userId);
            UserAuthenticationToken authenticationToken = new UserAuthenticationToken(pibleUser);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (Exception ex) {
            response.sendError(HttpStatus.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return Arrays.stream(ignored).anyMatch((ignore) -> request.getRequestURI().contains(ignore.substring(0, ignore.lastIndexOf("/*"))));
    }
}