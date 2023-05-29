package com.pible.common.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Converter
public class DateTimeConverter implements AttributeConverter<LocalDateTime, LocalDateTime> {
    @Override
    public LocalDateTime convertToDatabaseColumn(LocalDateTime attribute) {
        return attribute;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(LocalDateTime dbData) {
        return dbData.truncatedTo(ChronoUnit.SECONDS);
    }
}
