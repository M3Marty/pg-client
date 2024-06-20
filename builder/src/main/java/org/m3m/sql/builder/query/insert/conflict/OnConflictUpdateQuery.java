package org.m3m.sql.builder.query.insert.conflict;

import org.m3m.sql.builder.query.Query;
import org.m3m.sql.builder.query.insert.InsertQuery;
import org.m3m.sql.builder.query.update.UpdateQuery;

public class OnConflictUpdateQuery extends UpdateQuery {

	private final InsertQuery insertQuery;

	public OnConflictUpdateQuery(Object insertQuery) {
		this.insertQuery = (InsertQuery) insertQuery;
	}

	@Override
	public String buildExpression() {
		if (getSetExpressions().isEmpty()) {
			throw new IllegalStateException("Can't update without SET statement");
		}

		StringBuilder builder = new StringBuilder("DO UPDATE SET ")
				.append(String.join(",", getSetExpressions()));

		if (usedDataSource != null && usedDataSource.length != 0) {
			builder.append(" FROM ").append(usedDataSource[0].buildExpression());
			for (int i = 1; i < usedDataSource.length; i++) {
				builder.append(',').append(usedDataSource[i].buildExpression());
			}
		}

		if (!getWhereExpression().isEmpty()) {
			builder.append(getWhereExpression());
		}

		return builder.toString();
	}

	@Override
	public String build() {
		insertQuery.appendOnConflictExpression(buildExpression());
		return insertQuery.build();
	}

	@Override
	public void setReturningExpression(String returningExpression) {
		insertQuery.setReturningExpression(returningExpression);
	}
}