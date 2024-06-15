package org.m3m.sql.builder.query.delete;

import org.m3m.sql.builder.query.from.DataSource;

public interface UsingTables<T> {

	T using(DataSource...dataSource);
}
