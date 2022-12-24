package com.pible.config.database;

import org.hibernate.dialect.PostgreSQL10Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class CustomPGDialect extends PostgreSQL10Dialect {
    public CustomPGDialect() {
        super();
        registerFunction("string_agg", new SQLFunctionTemplate(StandardBasicTypes.STRING, "string_agg(?1, ?2)"));
    }
}
