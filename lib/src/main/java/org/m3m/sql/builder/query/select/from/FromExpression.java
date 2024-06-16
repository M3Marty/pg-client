package org.m3m.sql.builder.query.select.from;

import org.m3m.sql.builder.query.select.SelectQuery;

public interface FromExpression {

	StringBuilder getFromExpression();
	SelectQuery setFromExpression(StringBuilder expression);

	default SelectQuery appendFromExpression(String expression) {
		return setFromExpression(getFromExpression().append(' ').append(expression));
	}
}