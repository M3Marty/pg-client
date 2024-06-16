package org.m3m.sql.builder.query.update;

import lombok.Getter;
import lombok.Setter;
import org.m3m.sql.builder.query.Query;
import org.m3m.sql.builder.query.from.*;
import org.m3m.sql.builder.query.returning.FilterOrReturn;
import org.m3m.sql.builder.query.where.WhereQuery;

public class UpdateQuery implements SimpleFromAliasIn<UpdateSetOps<UpdateSetBuilder>>,
                                    Query, UpdateOps {

	@Getter
	@Setter
	private Query parent;

	protected DataSource dataSource;
	protected DataSource[] usedDataSource;

	@Setter
	protected String returningExpression;

	protected Query whereQuery;

	protected UpdateSetBuilder setBuilder;

	@Override
	public String build() {
		if (dataSource == null)
			throw new IllegalStateException("Can't update without data source");
		if (setBuilder == null)
			throw new IllegalStateException("Can't update without SET statement");

		StringBuilder builder = new StringBuilder("UPDATE ")
				.append(dataSource.buildExpression())
				.append(" SET ").append(setBuilder.buildExpression());

		if (usedDataSource != null && usedDataSource.length != 0) {
			builder.append(" FROM ").append(usedDataSource[0].buildExpression());
			for (int i = 1; i < usedDataSource.length; i++) {
				builder.append(',').append(usedDataSource[i].buildExpression());
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
	public String buildExpression() {
		return build();
	}

	@Override
	public UpdateSetBuilder from(TableDataSource dataSource) {
		this.dataSource = dataSource;
		return this.setBuilder = new UpdateSetBuilder(this);
	}

	@Override
	public FilterOrReturn<UpdateQuery> from(DataSource... dataSource) {
		this.usedDataSource = dataSource;
		return this;
	}

	@Override
	public WhereQuery<UpdateQuery> setWhereQuery(WhereQuery<UpdateQuery> whereQuery) {
		this.whereQuery = whereQuery;
		return whereQuery;
	}
}