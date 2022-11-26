package com.pible.config.advisor;

import com.pible.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvisor {

    @ExceptionHandler(CustomException.class)
    public String handleCustomException(Exception exception) {
        return exception.getMessage();
    }

}
