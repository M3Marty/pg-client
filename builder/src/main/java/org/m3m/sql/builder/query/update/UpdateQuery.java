package org.m3m.sql.builder.query.update;

import lombok.Getter;
import lombok.Setter;
import org.m3m.sql.builder.query.Query;
import org.m3m.sql.builder.query.from.*;
import org.m3m.sql.builder.query.returning.FilterOrReturn;

import java.util.ArrayList;
import java.util.List;

public class UpdateQuery implements SimpleFromAliasIn<UpdateSetOps>,
                                    UpdateSetOps, UpdateOps, Query {

	protected DataSource dataSource;
	protected DataSource[] usedDataSource;

	@Setter
	protected String returningExpression;

	@Getter
	private final StringBuilder whereExpression = new StringBuilder();

	@Getter
	protected List<String> setExpressions = new ArrayList<>();

	@Override
	public String build() {
		if (dataSource == null)
			throw new IllegalStateException("Can't update without data source");
		if (setExpressions.isEmpty())
			throw new IllegalStateException("Can't update without SET statement");

		StringBuilder builder = new StringBuilder("UPDATE ")
				.append(dataSource.buildExpression())
				.append(" SET ").append(String.join(",", setExpressions));

		if (usedDataSource != null && usedDataSource.length != 0) {
			builder.append(" FROM ").append(usedDataSource[0].buildExpression());
			for (int i = 1; i < usedDataSource.length; i++) {
				builder.append(',').append(usedDataSource[i].buildExpression());
			}
		}

		if (!getWhereExpression().isEmpty()) {
			builder.append(getWhereExpression());
		}

		if (returningExpression != null && !returningExpression.isEmpty()) {
			builder.append(' ').append(returningExpression);
		}

		return builder.toString();
	}

	@Override
	public String buildExpression() {
		return build();
	}

	@Override
	public UpdateSetOps from(TableDataSource dataSource) {
		this.dataSource = dataSource;
		return this;
	}

	@Override
	public FilterOrReturn from(DataSource... dataSource) {
		this.usedDataSource = dataSource;
		return this;
	}
}