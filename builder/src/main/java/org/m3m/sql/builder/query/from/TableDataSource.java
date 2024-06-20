package org.m3m.sql.builder.query.from;

import org.m3m.sql.builder.query.ValueQueryAs;

public class TableDataSource extends ValueQueryAs implements DataSource {

	public TableDataSource(String value) {
		super(value);
	}

	@Override
	public TableDataSource as(String value) {
		return (TableDataSource) super.as(value);
	}

	public TableDataSource as(String value, String...fieldAliases) {
		return as(value + " " + String.join(",", fieldAliases));
	}
}