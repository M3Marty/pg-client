package org.m3m.sql.builder.query.select.from;

import org.m3m.sql.builder.query.select.SelectQuery;

public interface FromExpression {

	StringBuilder getFromExpression();

	default SelectQuery appendFromExpression(String expression) {
		getFromExpression().append(' ').append(expression);
		return (SelectQuery) this;
	}
}