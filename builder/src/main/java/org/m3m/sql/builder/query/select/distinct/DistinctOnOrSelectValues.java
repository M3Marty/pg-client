package org.m3m.sql.builder.query.select.distinct;

import org.m3m.sql.builder.query.select.SelectValues;

public interface DistinctOnOrSelectValues extends DistinctOn, SelectValues {

	void setDistinctExpression(String s);

	@Override
	default SelectValues on(String...fields) {
		setDistinctExpression("DISTINCT ON (" + String.join(",", fields) + ") ");
		return this;
	}
}