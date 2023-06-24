package com.pible.config.sercurity.utils;

import com.pible.common.exception.JWTOmissionException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtils {
    private final Key key;
    private final long tokenValidMilliseconds;
    private final JwtParser parser;

    public JwtUtils(final String textKey) {
        key = Keys.hmacShaKeyFor(textKey.getBytes());
        tokenValidMilliseconds = 1000L * 60;
        parser = Jwts.parserBuilder().setSigningKey(key).build();
    }

    public String createToken(String subject, Map<String, Object> data, long tokenValidMilliseconds) {
        Claims claims = Jwts.claims().setSubject(subject);
        claims.putAll(data);
        Date now = new Date();
        return Jwts.builder().setClaims(claims)
                .setHeaderParam("typ", "JWT")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMilliseconds))
                .signWith(key)
                .compact();
    }

    public String createToken(String subject, Map<String, Object> data) {
        return createToken(subject, data, tokenValidMilliseconds);
    }

    public boolean validateToken(String token) {
        if (!StringUtils.hasLength(token)) {
            throw new JWTOmissionException("JWT token is not existed");
        }

        try {
            parser.parseClaimsJws(token);
        } catch (SignatureException e) {
            log.error("Invalid JWT signature", e);
            throw e;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token", e);
            throw e;
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token");
            throw e;
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token", e);
            throw e;
        }

        return true;
    }

    public Claims getData(String token) {
        return parser.parseClaimsJws(token).getBody();
    }

    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }
}