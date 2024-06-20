package org.m3m.sql.builder.query.where;

import org.m3m.sql.builder.Sql;

public interface WhereOps<T extends WhereOps<?>> {

	void appendCondition(String expression);

	static String in(Object value) {
		return " IN " + Sql.getObjectStringValue(value);
	}

	static String eq(Object value) {
		return " = " + Sql.getObjectStringValue(value);
	}

	static String notEq(Object value) {
		return " <> " + Sql.getObjectStringValue(value);
	}

	static String lsThan(Object value) {
		return " < " + Sql.getObjectStringValue(value);
	}

	static String like(Object value) {
		return " LIKE " + Sql.getObjectStringValue(value);
	}

	static String grThan(Object value) {
		return " > " + Sql.getObjectStringValue(value);
	}

	default T and(String field, String expression) {
		return and(field + expression);
	}

	default T or(String field, String expression) {
		return or(field + expression);
	}

	@SuppressWarnings("unchecked")
	default T and(String expression) {
		appendCondition("AND " + expression);
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	default T or(String expression) {
		appendCondition("OR " + expression);
		return (T) this;
	}
}