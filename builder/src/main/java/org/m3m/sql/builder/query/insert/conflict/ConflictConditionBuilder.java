package org.m3m.sql.builder.query.insert.conflict;

import org.m3m.sql.builder.query.where.WhereOps;

public interface ConflictConditionBuilder
		extends OnConflict, WhereOps<ConflictConditionBuilder> {

	@Override
	default void appendCondition(String expression) {
		appendOnConflictExpression(expression);
	}
}