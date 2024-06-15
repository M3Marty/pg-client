package org.m3m.sql.builder.query;

import lombok.*;

@Getter
public class ValueQuery implements Query {

	@Setter
	private Query parent;

	protected String value;

	@Override
	public String buildExpression() {
		return value;
	}

	public ValueQuery(String value) {
		this.value = value;
	}
}