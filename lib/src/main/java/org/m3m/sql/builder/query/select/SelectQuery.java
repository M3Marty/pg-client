package org.m3m.sql.builder.query.select;

import lombok.*;
import org.m3m.sql.builder.query.Query;
import org.m3m.sql.builder.query.select.distinct.*;
import org.m3m.sql.builder.query.select.from.*;
import org.m3m.sql.builder.query.select.group.GroupingSelectOps;
import org.m3m.sql.builder.query.select.range.RangableSelect;

public class SelectQuery implements DistinctableSelect, DistinctOrSelectValues,
                                    DistinctOnOrSelectValues,
                                    SelectValues,
                                    FromBuilder, JoinableAndSamplableTable,
                                    JoiningCondition, JoiningConditionBuilder,
                                    SelectOps, Query,
                                    GroupingSelectOps,
                                    RangableSelect {

	@Setter
	private String distinctExpression = "";

	private String selectValuesExpression;

	@Getter
	private final StringBuilder fromExpression = new StringBuilder();

	@Getter
	private final StringBuilder whereExpression = new StringBuilder();

	@Getter
	private final StringBuilder groupExpression = new StringBuilder();

	@Getter
	private final StringBuilder tableExpression = new StringBuilder();

	@Getter
	private final StringBuilder orderExpression = new StringBuilder();

	@Getter
	private final StringBuilder rangeExpression = new StringBuilder();

	@Getter
	private final StringBuilder blockExpression = new StringBuilder();

	@Override
	public String build() {
		if (selectValuesExpression == null || selectValuesExpression.isEmpty()
				|| distinctExpression.isEmpty()
				|| fromExpression.isEmpty()) {
			throw new IllegalStateException("Incorrect select expression");
		}

		StringBuilder builder = new StringBuilder("SELECT ")
				.append(distinctExpression)
				.append(selectValuesExpression)
				.append(" FROM").append(fromExpression);

		if (!getWhereExpression().isEmpty()) {
			builder.append(getWhereExpression());
		}

		if (!getGroupExpression().isEmpty()) {
			builder.append(' ').append(getGroupExpression());
		}

		if (!getTableExpression().isEmpty()) {
			builder.append(getTableExpression());
		}

		if (!getOrderExpression().isEmpty()) {
			builder.append(getOrderExpression());
		}

		if (!getRangeExpression().isEmpty()) {
			builder.append(getRangeExpression());
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

	@Override
	public RangableSelect orderBy(String expression) {
		orderExpression.append("ORDER BY").append(expression);
		return this;
	}
}