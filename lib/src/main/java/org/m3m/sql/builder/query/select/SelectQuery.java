package org.m3m.sql.builder.query.select;

import lombok.Getter;
import lombok.Setter;
import org.m3m.sql.builder.query.Query;
import org.m3m.sql.builder.query.select.distinct.*;
import org.m3m.sql.builder.query.select.from.*;

public class SelectQuery implements DistinctableSelect, DistinctOrSelectValues,
                                    DistinctOnOrSelectValues,
                                    SelectValues,
                                    FromBuilder, JoinableAndSamplableTable,
                                    JoiningCondition, JoiningConditionBuilder,
                                    SelectOps, Query {

	@Setter
	private String distinctExpression = "";

	private String selectValuesExpression;

	@Getter(lazy = true)
	private final StringBuilder fromExpression = new StringBuilder();

	@Override
	public String build() {
		// TODO checks

		StringBuilder builder = new StringBuilder("SELECT ")
				.append(distinctExpression)
				.append(selectValuesExpression)
				.append(" FROM ").append(fromExpression);

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
}