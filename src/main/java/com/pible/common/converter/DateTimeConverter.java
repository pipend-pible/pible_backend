package com.pible.common.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Converter
// 프론트에 DB와는 다른 형식으로 시간값을 보내기위한 설정입니다.
public class DateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
        return attribute == null ? null : Timestamp.valueOf(attribute);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
        return dbData == null ? null : dbData.toLocalDateTime().truncatedTo(ChronoUnit.SECONDS);
    }
}
