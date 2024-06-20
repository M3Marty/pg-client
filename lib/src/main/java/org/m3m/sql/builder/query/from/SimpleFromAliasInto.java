package org.m3m.sql.builder.query.from;

public interface SimpleFromAliasInto<T> extends SimpleFrom<T> {

	default T into(TableDataSource dataSource) {
		return from(dataSource);
	}

	default T into(String tableName) {
		return from(tableName);
	}
}