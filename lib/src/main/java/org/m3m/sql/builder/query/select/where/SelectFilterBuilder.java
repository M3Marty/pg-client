package org.m3m.sql.builder.query.select.where;

import lombok.RequiredArgsConstructor;
import org.m3m.sql.builder.query.Query;
import org.m3m.sql.builder.query.select.SelectQuery;
import org.m3m.sql.builder.query.where.WhereOps;

@RequiredArgsConstructor
public class SelectFilterBuilder implements WhereOps<SelectFilterBuilder>,
                                            FilteredSelectOps, Query {

	private final SelectQuery parentQuery;

	@Override
	public String build() {
		return parentQuery.build();
	}

	@Override
	public String buildExpression() {
		return "";
	}

	@Override
	public void appendCondition(String expression) {
		parentQuery.appendWhereExpression(expression);
	}
}