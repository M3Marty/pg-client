package org.m3m.sql.builder.query;

public class ValueQuery implements Query {

	protected String value;

	@Override
	public String buildExpression() {
		return value;
	}

	public ValueQuery(String value) {
		this.value = value;
	}
}