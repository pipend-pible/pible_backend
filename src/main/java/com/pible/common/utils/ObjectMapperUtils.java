package com.pible.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.pible.common.enums.ResponseCode;
import com.pible.common.exception.BusinessException;

public class ObjectMapperUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

    public static <T> T readValue(String source, Class<T> target) {
        try {
            return objectMapper.readValue(source, target);
        } catch (JsonProcessingException jsonProcessingException) {
            throw new BusinessException(ResponseCode.ERROR_JSON_PROCESSING);
        }
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
