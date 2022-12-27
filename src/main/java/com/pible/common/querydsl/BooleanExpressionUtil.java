package com.pible.common.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

public class BooleanExpressionUtil {

    public static <T> BooleanExpression containsList(SimpleExpression<T> path, List<T> list) {
        if(CollectionUtils.isEmpty(list)) {
            return null;
        }

        return path.in(list);
    }

    public static BooleanExpression contains(StringExpression path, String value) {
        if(!StringUtils.hasLength(value)) {
            return null;
        }

        return path.contains(value);
    }

    public static <T> BooleanExpression eq(SimpleExpression<T> path, T value) {
        if(value == null) {
            return null;
        }

        return path.eq(value);
    }
}
