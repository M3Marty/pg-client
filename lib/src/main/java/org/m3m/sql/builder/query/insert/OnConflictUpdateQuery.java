package org.m3m.sql.builder.query.insert;

import lombok.AllArgsConstructor;
import org.m3m.sql.builder.query.update.UpdateQuery;

@AllArgsConstructor
public class OnConflictUpdateQuery extends UpdateQuery {

	private InsertQuery insertQuery;

	@Override
	public String buildExpression() {
		if (setBuilder == null)
			throw new IllegalStateException("Can't update without SET statement");

		StringBuilder builder = new StringBuilder("DO UPDATE SET ")
				.append(setBuilder.buildExpression());

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
	public String build() {
		insertQuery.appendOnConflictExpression(buildExpression());
		return insertQuery.build();
	}

	@Override
	public void setReturningExpression(String returningExpression) {
		insertQuery.setReturningExpression(returningExpression);
	}
}