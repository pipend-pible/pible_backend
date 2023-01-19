package com.pible.common.exception;

import com.pible.common.enums.ResponseCode;
import com.pible.common.response.PibleResponse;
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
    public PibleResponse handleBusinessException(BusinessException e) {
        log.error(e.getResponseCode().getMessage());
        return new PibleResponse(e.getResponseCode());
    }

    @ExceptionHandler(Exception.class)
    public PibleResponse handleException(Exception e) {
        log.error(e.getMessage());
        return new PibleResponse(ResponseCode.FAIL);
    }
}
