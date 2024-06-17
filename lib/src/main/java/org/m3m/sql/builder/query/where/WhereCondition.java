package org.m3m.sql.builder.query.where;

import org.m3m.sql.builder.query.Query;

public interface WhereCondition<T extends WhereOps<?>>
		extends WhereExpression<T>, Query {

	default T where(String expression) {
		return appendWhereExpression("WHERE " + expression);
	}

	default T where(String a, String b) {
		return where(a + b);
	}
}