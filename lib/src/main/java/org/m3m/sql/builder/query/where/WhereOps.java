package org.m3m.sql.builder.query.where;

import org.m3m.sql.builder.query.Query;
import org.m3m.sql.builder.query.ValueQuery;

public interface WhereOps<T extends WhereOps<?>> {

	private static String getObjectStringValue(Object value) {
		return switch (value) {
			case String str -> String.format("'%s'", str);
			case Number num -> num.toString();
			case Query query -> query.buildExpression();
			default -> throw new IllegalArgumentException("Unsupported type: "
					+ value.getClass().getSimpleName());
		};
	}

	static String eq(Object value) {
		return " = " + getObjectStringValue(value);
	}

	static String notEq(Object value) {
		return " <> " + getObjectStringValue(value);
	}

	static String lessThan(Object value) {
		return " < " + getObjectStringValue(value);
	}

	void appendCondition(String expression);

	default T and(String field, String expression) {
		return and(field + expression);
	}

	default T or(String field, String expression) {
		return or(field + expression);
	}

	@SuppressWarnings("unchecked")
	default T and(String expression) {
		appendCondition(" AND " + expression);
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	default T or(String expression) {
		appendCondition(" OR " + expression);
		return (T) this;
	}
}