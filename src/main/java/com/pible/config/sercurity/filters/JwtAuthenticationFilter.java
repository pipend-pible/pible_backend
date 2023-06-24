package com.pible.config.sercurity.filters;

import com.pible.config.sercurity.model.PibleUser;
import com.pible.config.sercurity.model.UserAuthenticationToken;
import com.pible.config.sercurity.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
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
        try {
            String jwt = jwtUtils.getJwtFromRequest(request);
            if (StringUtils.isNotEmpty(jwt) && jwtUtils.validateToken(jwt)) {
                Claims claims = jwtUtils.getData(jwt);
                String nickName = String.valueOf(claims.get("nickName"));
                List<GrantedAuthority> authorities = (List)claims.get("authorities");

                PibleUser pibleUser = new PibleUser(claims.getSubject(), null, authorities, nickName);
                UserAuthenticationToken authenticationToken = new UserAuthenticationToken(pibleUser);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else {
                if (StringUtils.isEmpty(jwt)) {
                    request.setAttribute("unauthorization", "401 인증키 없음.");
                }

                if (jwtUtils.validateToken(jwt)) {
                    request.setAttribute("unauthorization", "401-001 인증키 만료.");
                }
            }
        } catch (Exception ex) {
            log.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return Arrays.stream(ignored).anyMatch((ignore) -> request.getRequestURI().contains(ignore.substring(0, ignore.lastIndexOf("/*"))));
    }
}