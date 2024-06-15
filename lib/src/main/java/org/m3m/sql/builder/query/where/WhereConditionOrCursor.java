package org.m3m.sql.builder.query.where;

public interface WhereConditionOrCursor<T> extends WhereCondition<T> {

	default WhereQuery<T> whereCurrentOf(String cursor) {
		return setWhereQuery(new WhereQuery<>(this, "CURRENT OF " + cursor));
	}
}