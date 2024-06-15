package org.m3m.sql.builder.query.where;

public interface WhereOps<T extends WhereOps<?>> {

	private static String getObjectStringValue(Object value) {
		return switch (value) {
			case String str -> String.format("'%s'", str);
			case Number num -> num.toString();
			default -> throw new IllegalArgumentException("Unsupported type: "
					+ value.getClass().getSimpleName());
		};
	}

	static String eq(Object value) {
		return " = " + getObjectStringValue(value);
	}

	static String eqField(String field) {
		return " = " + field;
	}

	static String notEq(Object value) {
		return " <> " + getObjectStringValue(value);
	}

	static String notEqField(String field) {
		return " <> " + field;
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