package org.m3m.sql.builder.query.where;

import org.m3m.sql.builder.query.returning.Returning;

public interface ReturningConditionBuilder extends Returning,
                                                   WhereExpression<ReturningConditionBuilder>,
                                                   WhereOps<ReturningConditionBuilder> {

	@Override
	default void appendCondition(String expression) {
		appendWhereExpression(expression);
	}
}