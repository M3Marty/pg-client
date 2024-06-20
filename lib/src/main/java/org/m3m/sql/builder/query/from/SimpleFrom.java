package org.m3m.sql.builder.query.from;

public interface SimpleFrom<T> {

	T from(TableDataSource dataSource);

	default T from(String tableName) {
		return from(new TableDataSource(tableName));
	}
}