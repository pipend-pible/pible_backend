package com.pible.common;

import com.pible.common.enums.ResponseCode;
import com.pible.common.response.PibleResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class CustomResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                         MediaType selectedContentType,
                                         Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                         ServerHttpRequest request, ServerHttpResponse response) {
        if(body == null || body instanceof PibleResponse) {
            return body;
        }

        return new PibleResponse(ResponseCode.SUCCESS, body);
    }
}
