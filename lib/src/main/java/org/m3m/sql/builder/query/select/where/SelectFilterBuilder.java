package org.m3m.sql.builder.query.select.where;

import lombok.RequiredArgsConstructor;
import org.m3m.sql.builder.query.Query;
import org.m3m.sql.builder.query.select.SelectQuery;
import org.m3m.sql.builder.query.select.group.GroupingSelectOps;
import org.m3m.sql.builder.query.select.range.RangableSelect;
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
		return parentQuery.buildExpression();
	}

	@Override
	public void appendCondition(String expression) {
		parentQuery.getWhereExpression().append(' ').append(expression);
	}

	@Override
	public GroupingSelectOps groupBy(String expression) {
		return parentQuery.groupBy(expression);
	}

	@Override
	public StringBuilder getTableExpression() {
		return parentQuery.getTableExpression();
	}

	@Override
	public RangableSelect orderBy(String expression) {
		return parentQuery.orderBy(expression);
	}

	@Override
	public StringBuilder getBlockExpression() {
		return parentQuery.getBlockExpression();
	}
}