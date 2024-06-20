package org.m3m.sql.builder.query.select.group;

import org.m3m.sql.builder.query.*;
import org.m3m.sql.builder.query.select.SelectQuery;

public interface GroupingSelectOps extends Query {

	default GroupFilterBuilder having(String expression) {
		var builder = new GroupFilterBuilder((SelectQuery) this);
		builder.appendCondition("HAVING " + expression);
		return builder;
	}

	default GroupFilterBuilder having(ValueQuery expression, String operation) {
		return having(expression.buildExpression() + operation);
	}

	default GroupFilterBuilder having(String field, String operation) {
		return having(field + operation);
	}
}