package org.m3m.sql.builder.query.returning;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.m3m.sql.builder.query.Query;

public interface Returning extends Query {

	static String buildExpression(String expression) {
		return "RETURNING " + expression;
	}

	void setReturningExpression(String expression);

	private String returning(String expression) {
		setReturningExpression(buildExpression(expression));
		return build();
	}

	default String returning(String...entries) {
		return returning(String.join(",", entries));
	}

	default String returning(ReturningType type) {
		setReturningExpression(type.getExpression());
		return build();
	}

	static ReturningType none() {
		return ReturningType.NONE;
	}

	static ReturningType all() {
		return ReturningType.ALL;
	}

	static ReturningType count() {
		return ReturningType.COUNT;
	}

	@Getter
	@RequiredArgsConstructor
	enum ReturningType {
		NONE(""), ALL("RETURNING *"), COUNT("RETURNING COUNT(*)");

		private final String expression;
	}
}