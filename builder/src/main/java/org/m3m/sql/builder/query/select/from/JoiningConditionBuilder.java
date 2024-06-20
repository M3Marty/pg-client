package org.m3m.sql.builder.query.select.from;

import org.m3m.sql.builder.query.select.SelectOps;
import org.m3m.sql.builder.query.where.WhereOps;

public interface JoiningConditionBuilder extends SelectOps, FromExpression,
                                                 JoinableFromElement,
                                                 WhereOps<JoiningConditionBuilder> {

	@Override
	default void appendCondition(String expression) {
		appendFromExpression(expression);
	}
}