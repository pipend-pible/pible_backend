package com.pible.common.exception;

import com.pible.common.enums.ResponseCode;
import com.pible.common.response.PibleResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@ResponseStatus(HttpStatus.OK)
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public PibleResponse handleException(BusinessException e) {
        log.error(e.getResponseCode().getMessage(), e);
        return new PibleResponse(e.getResponseCode());
    }

    @ExceptionHandler(SignatureException.class)
    public PibleResponse handleException(SignatureException e) {
        log.error(e.getMessage(), e);
        return new PibleResponse(ResponseCode.NOT_INVALIDATED_SIGNATURE);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public PibleResponse handleException(MalformedJwtException e) {
        log.error(e.getMessage(), e);
        return new PibleResponse(ResponseCode.MALFORMED_TOKEN);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public PibleResponse handleException(ExpiredJwtException e) {
        log.error(e.getMessage(), e);
        return new PibleResponse(ResponseCode.EXPIRED_TOKEN);
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    public PibleResponse handleException(UnsupportedJwtException e) {
        log.error(e.getMessage(), e);
        return new PibleResponse(ResponseCode.UNSUPPORTED_TOKEN);
    }

    @ExceptionHandler(Exception.class)
    public PibleResponse handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new PibleResponse(ResponseCode.FAIL);
    }

}
