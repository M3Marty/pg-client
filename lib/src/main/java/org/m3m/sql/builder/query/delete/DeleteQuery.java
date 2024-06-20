package org.m3m.sql.builder.query.delete;

import lombok.Getter;
import lombok.Setter;
import org.m3m.sql.builder.query.Query;
import org.m3m.sql.builder.query.from.*;
import org.m3m.sql.builder.query.returning.FilterOrReturn;

public class DeleteQuery implements SimpleFrom<DeleteOps>, DeleteOps, Query {

	private DataSource dataSource;

	private DataSource[] usedDataSource;

	@Setter
	private String returningExpression;

	@Getter
	private final StringBuilder whereExpression = new StringBuilder();

	@Override
	public String buildExpression() {
		return build();
	}

	@Override
	public String build() {
		if (dataSource == null)
			throw new IllegalStateException("Can't delete without data source");

		StringBuilder builder = new StringBuilder("DELETE FROM ")
				.append(dataSource.buildExpression());

		if (usedDataSource != null && usedDataSource.length != 0) {
			builder.append(" USING ").append(usedDataSource[0].buildExpression());
			for (int i = 1; i < usedDataSource.length; i++) {
				builder.append(",").append(usedDataSource[i].buildExpression());
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
	public DeleteOps from(TableDataSource dataSource) {
		this.dataSource = dataSource;
		return this;
	}

	@Override
	public FilterOrReturn using(DataSource...dataSource) {
		this.usedDataSource = dataSource;
		return this;
	}
}