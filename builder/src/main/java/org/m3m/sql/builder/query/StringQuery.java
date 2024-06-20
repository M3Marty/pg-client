package org.m3m.sql.builder.query;

import lombok.Setter;
import org.m3m.sql.builder.Sql;

public class StringQuery extends ValueQuery {

	public StringQuery(Object value) {
		super(Sql.getObjectStringValue(value));
	}

	public StringQuery cat(Object value) {
		this.value += " || " + Sql.getObjectStringValue(value);
		return this;
	}
}