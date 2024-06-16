package org.m3m.sql.builder.query.from;

public interface SimpleFrom<T> {

	T from(TableDataSource dataSource);
}