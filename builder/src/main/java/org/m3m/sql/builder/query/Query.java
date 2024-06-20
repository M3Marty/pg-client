package org.m3m.sql.builder.query;

public interface Query {

	default String build() {
		return buildExpression();
	}

	String buildExpression();
}