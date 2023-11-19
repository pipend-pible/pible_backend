package com.pible.config.database;

import org.hibernate.dialect.PostgreSQL10Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

// string_agg 라는 postgresql function을 사용하기 위해 등록한 설정입니다.
public class CustomPGDialect extends PostgreSQL10Dialect {
    public CustomPGDialect() {
        super();
        registerFunction("string_agg", new SQLFunctionTemplate(StandardBasicTypes.STRING, "string_agg(?1, ?2)"));
    }
}
