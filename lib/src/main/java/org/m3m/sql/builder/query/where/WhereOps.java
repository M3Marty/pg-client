package org.m3m.sql.builder.query.where;

import org.m3m.sql.builder.Sql;

public interface WhereOps<T extends WhereOps<?>> {

	static String eq(Object value) {
		return " = " + Sql.getObjectStringValue(value);
	}

	static String notEq(Object value) {
		return " <> " + Sql.getObjectStringValue(value);
	}

	static String lessThan(Object value) {
		return " < " + Sql.getObjectStringValue(value);
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