package com.pible.config.sercurity.utils;

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
        tokenValidMilliseconds = 1000L * 60 * 60 * 2;
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
            log.error("token is not existed");
            return false;
        }

        try {
            parser.parseClaimsJws(token);
        } catch (SignatureException signatureException) {
            log.error("invalid JWT signature", signatureException);
            throw signatureException;
        } catch (MalformedJwtException malformedJwtException) {
            log.error("invalid JWT token", malformedJwtException);
            throw malformedJwtException;
        } catch (ExpiredJwtException expiredJwtException) {
            log.error("expired JWT token", expiredJwtException);
            throw expiredJwtException;
        } catch (UnsupportedJwtException unsupportedJwtException) {
            log.error("unsupported JWT token", unsupportedJwtException);
            throw unsupportedJwtException;
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