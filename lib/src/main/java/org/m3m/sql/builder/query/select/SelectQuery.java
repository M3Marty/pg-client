package org.m3m.sql.builder.query.select;

import lombok.*;
import org.m3m.sql.builder.query.Query;
import org.m3m.sql.builder.query.select.distinct.*;
import org.m3m.sql.builder.query.select.from.*;
import org.m3m.sql.builder.query.select.group.GroupingSelectOps;

public class SelectQuery implements DistinctableSelect, DistinctOrSelectValues,
                                    DistinctOnOrSelectValues,
                                    SelectValues,
                                    FromBuilder, JoinableAndSamplableTable,
                                    JoiningCondition, JoiningConditionBuilder,
                                    SelectOps, Query,
                                    GroupingSelectOps {

	@Setter
	private String distinctExpression = "";

	private String selectValuesExpression;

	@Getter
	private final StringBuilder fromExpression = new StringBuilder();

	@Getter
	private final StringBuilder whereExpression = new StringBuilder();

	@Getter
	private final StringBuilder groupExpression = new StringBuilder();

	@Override
	public String build() {
		// TODO checks

		StringBuilder builder = new StringBuilder("SELECT ")
				.append(distinctExpression)
				.append(selectValuesExpression)
				.append(" FROM").append(fromExpression);

		if (!getWhereExpression().isEmpty()) {
			builder.append(whereExpression);
		}

		if (!getGroupExpression().isEmpty()) {
			builder.append(' ').append(groupExpression);
		}

		return builder.toString();
	}

	@Override
	public String buildExpression() {
		return build();
	}

	@Override
	public DistinctOnOrSelectValues distinct() {
		this.distinctExpression = "DISTINCT ";
		return this;
	}

	@Override
	public SelectQuery setSelectValuesExpression(String expression) {
		selectValuesExpression = expression;
		return this;
	}

	@Override
	public GroupingSelectOps groupBy(String expression) {
		groupExpression.append("GROUP BY ").append(expression);
		return this;
	}
}