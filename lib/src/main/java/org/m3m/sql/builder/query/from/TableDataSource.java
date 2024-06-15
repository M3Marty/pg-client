package org.m3m.sql.builder.query.from;

import org.m3m.sql.builder.query.ValueQueryAs;

public class TableDataSource extends ValueQueryAs implements DataSource {

	public TableDataSource(String value) {
		super(value);
	}
}
