package org.m3m.sql.builder.query.from;

public interface SimpleFromAliasIn<T> extends SimpleFrom<T> {

	default T in(TableDataSource dataSource) {
		return from(dataSource);
	}

	default T in(String tableName) {
		return from(tableName);
	}
}
