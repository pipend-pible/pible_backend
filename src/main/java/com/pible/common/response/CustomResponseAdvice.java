package com.pible.common.response;

import com.pible.common.enums.ResponseCode;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
// 공통된 response를 위해 추가한 설정이나 사이드이펙트가 발생하는 경우가 많아 제거를 고려했던 설정입니다.
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
        if(body instanceof PibleResponse || body instanceof String) {
            return body;
        }

        return new PibleResponse(ResponseCode.SUCCESS, body);
    }
}
