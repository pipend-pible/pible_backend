package com.pible.config.sercurity.filters;

import com.pible.config.sercurity.model.PibleUser;
import com.pible.config.sercurity.model.UserAuthenticationToken;
import com.pible.config.sercurity.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
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
            if(!jwtUtils.validateToken(jwt)) {
                response.sendError(HttpStatus.SC_UNAUTHORIZED);
                return;
            }

            Claims claims = jwtUtils.getData(jwt);
            String nickName = String.valueOf(claims.get("nickName"));
            List<LinkedHashMap<String, String>> linkedAuthorities = (List<LinkedHashMap<String, String>>) claims.get("authorities");
            Set<GrantedAuthority> authorities = linkedAuthorities.stream().flatMap(linkedHashMap -> linkedHashMap.values().stream().map(SimpleGrantedAuthority::new)).collect(Collectors.toSet());

            PibleUser pibleUser = new PibleUser(claims.getSubject(), "temporal", authorities, nickName);
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