package org.m3m.sql.builder.query.delete;

import lombok.Getter;
import lombok.Setter;
import org.m3m.sql.builder.query.Query;
import org.m3m.sql.builder.query.from.*;
import org.m3m.sql.builder.query.returning.FilterOrReturn;
import org.m3m.sql.builder.query.where.WhereQuery;

public class DeleteQuery implements Query, SimpleFrom<DeleteOps>, DeleteOps {

	@Getter @Setter
	private Query parent;

	private DataSource dataSource;
	private DataSource[] usedDataSource;

	@Setter
	private String returningExpression;

	private Query whereQuery;

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
				builder.append(", ").append(usedDataSource[i].buildExpression());
			}
		}

		if (whereQuery != null) {
			builder.append(" WHERE ").append(whereQuery.buildExpression());
		}

		if (returningExpression != null && !returningExpression.isEmpty()) {
			builder.append(' ').append(returningExpression);
		}

		return builder.toString();
	}

	@Override
	public DeleteOps from(TableDataSource dataSource) {
		this.dataSource = dataSource;
		this.dataSource.setParent(this);
		return this;
	}

	@Override
	public FilterOrReturn<DeleteQuery> using(DataSource...dataSource) {
		this.usedDataSource = dataSource;
		if (this.usedDataSource != null) {
			for (var ds : usedDataSource) {
				ds.setParent(this);
			}
		}

		return this;
	}

	@Override
	public WhereQuery<DeleteQuery> setWhereQuery(WhereQuery<DeleteQuery> whereQuery) {
		this.whereQuery = whereQuery;
		this.whereQuery.setParent(this);
		return whereQuery;
	}
}