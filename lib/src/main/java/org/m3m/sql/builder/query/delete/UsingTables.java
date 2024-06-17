package org.m3m.sql.builder.query.delete;

import org.m3m.sql.builder.query.from.DataSource;
import org.m3m.sql.builder.query.returning.FilterOrReturn;

public interface UsingTables {

	FilterOrReturn using(DataSource...dataSource);
}
