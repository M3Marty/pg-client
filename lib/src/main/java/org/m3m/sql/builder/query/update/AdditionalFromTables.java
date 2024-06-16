package org.m3m.sql.builder.query.update;

import org.m3m.sql.builder.query.from.DataSource;

public interface AdditionalFromTables<T> {

	T from(DataSource...dataSource);
}