package org.m3m.sql.builder.query.select.group;

import lombok.RequiredArgsConstructor;
import org.m3m.sql.builder.query.Query;
import org.m3m.sql.builder.query.select.SelectQuery;
import org.m3m.sql.builder.query.where.WhereOps;

@RequiredArgsConstructor
public class GroupFilterBuilder implements WhereOps<GroupFilterBuilder>, Query {

	private final SelectQuery parentQuery;

	@Override
	public void appendCondition(String expression) {
		parentQuery.getGroupExpression().append(' ').append(expression);
	}

	@Override
	public String build() {
		return parentQuery.build();
	}

	@Override
	public String buildExpression() {
		return "";
	}
}
