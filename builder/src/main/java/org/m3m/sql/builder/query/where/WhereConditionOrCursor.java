package org.m3m.sql.builder.query.where;

public interface WhereConditionOrCursor<T extends WhereOps<?>>
		extends WhereCondition<T> {

	default T whereCurrentOf(String cursor) {
		return appendWhereExpression("WHERE CURRENT OF " + cursor);
	}
}